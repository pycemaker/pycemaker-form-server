from pycemaker import Pycemaker

spring_app = Pycemaker(prom_url='http://localhost:9090/api/v1/query',
    db_host='localhost',
    db_port='27017',
    db='pycemaker',
    collection='appdata')

#spring_app.save_data(doc_count=10, time_to_save=30)
#spring_app.export_data_csv()
#spring_app.export_data_json(Path.cwd())
