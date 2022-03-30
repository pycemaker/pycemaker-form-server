import array
import smtplib
import os
from email.message import EmailMessage
from email.mime.image import MIMEImage
from dotenv import load_dotenv

load_dotenv('.env')

email_from = os.environ.get('EMAIL_FROM')
password = os.environ.get('PASSWORD')

def generate_body(email_to: str, image_paths: array, msg: EmailMessage()):
    msg['Subject'] = 'Este é um e-mail em HTML com imagens anexadas'
    msg['From'] = email_from
    msg['To'] = email_to
    #msg['To'] = (", ").join(email_to)

    # Plain text
    msg.set_content('This is a plain text email')

    # HTML Body
    text_part = msg.iter_parts()
    text_part
    msg.add_alternative("""\
    <!DOCTYPE html>
    <html>
        <body>
            <p>Hi,</p>
            <p>If you are seeing this, it means that you have received my email. Check out these images!</p>
            <img src="cid:image1" ><br>
            <img src="cid:image2" ><br>
        </body>
    </html>
    """.format(os.path.basename(image_paths[0]), os.path.basename(image_paths[1])), subtype='html')
    return msg


def attach_images(msg: EmailMessage(), image_paths: array) -> EmailMessage():
    counter = 1
    for file_path in image_paths:
        filename = os.path.basename(file_path)
        file_path = open(file_path, 'rb')
        msgImage = MIMEImage(file_path.read())
        file_path.close()
        # Define the image's ID as referenced above
        msgImage.add_header('Content-ID', '<image'+str(counter)+'>')
        msgImage.add_header('Content-Disposition',
                            'attachment', filename=filename)
        msg.attach(msgImage)
        counter += 1
    return msg


def dispatch_email(email_to: str, image_paths: array):

    msg = EmailMessage()
    msg = generate_body(email_to, image_paths, msg)
    msg = attach_images(msg, image_paths)

    print("Enviando e-mail, aguarde...")

    with smtplib.SMTP_SSL('smtp.gmail.com', 465) as smtp:
        smtp.login(email_from, password)
        smtp.send_message(msg)

    print("E-mail enviado com sucesso!")


email_to = ['diegorodrigocpv@gmail.com']
image_paths = ["D:/chatillon/Área de Trabalho/4XWBU7q.png",
               "D:/chatillon/Área de Trabalho/oKt2km9.png"]

dispatch_email(email_to, image_paths)
