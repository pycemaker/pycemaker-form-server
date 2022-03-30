from pymongo import MongoClient
from pycemaker import Pycemaker, date_conv, percentage_conv
from bson.json_util import dumps
import pandas as pd


spring_app = Pycemaker('http://localhost:9090/api/v1/query')
client = MongoClient()


# time.sleep(5)
# data = spring_app.get_cpu_usage()[0]
# cpu_data = spring_app.get_cpu_usage()[1]

# cpu = {
#     "data": str(data),
#     "cpu_used": str(cpu_data)
# }

db = client.pycemaker
cpu_usage = db.cpu_usage
# result = cpu_usage.insert_one(cpu)


cursor = cpu_usage.find({})
# with open('cpu_usage.json', 'w') as file:
#     file.write('[')
#     for document in cursor:
#         file.write(dumps(document))
#         file.write(',')
#     file.write(']')

docs = pd.DataFrame(cursor)
    # Discard the Mongo ID for the documents
docs.pop("_id")

docs.to_csv(r'cpu_usage.csv', index=None)
