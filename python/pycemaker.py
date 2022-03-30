import datetime
import time

import requests
from hurry.filesize import si, size
from rfc3339 import rfc3339


#https://builtin.com/data-science/time-series-python



# import pandas


# def porcentagem(x):
#     percentage = "{:.2%}".format(abs(float(x)))
#     return percentage

# PROMETHEUS_URL = 'http://localhost:9090/api/v1/query'
# response = requests.get(PROMETHEUS_URL, params={'query': 'process_cpu_usage'})
# results = response.json()['data']['result'][0]['value'][1]
# print(porcentagem(results))


def date_conv(ms):
    dt = datetime.datetime.fromtimestamp(ms, tz=datetime.timezone.utc)
    epoch = time.mktime(dt.timetuple())
    offset = datetime.datetime.fromtimestamp(
        epoch) - datetime.datetime.utcfromtimestamp(epoch)
    data_date = rfc3339(dt + offset, utc=True, use_system_timezone=False)
    return data_date

def percentage_conv(n):
    percentage_data = "{:.2%}".format(abs(float(n)))
    return percentage_data


class Pycemaker:
    def __init__(self,prom_url):
        self.prom_url = prom_url

    def get_cpu_usage(self):

        response = requests.get(self.prom_url, params={
                                'query': 'process_cpu_usage'})
        
        result = (response.json()['data']['result'][0]['value'][0],
                  response.json()['data']['result'][0]['value'][1])

        time_data = date_conv(result[0])
        percentage_data = percentage_conv(result[1])

        return (time_data, percentage_data)
    
    def get_used_memory(self):
        response = requests.get(self.prom_url, params={
                            'query': 'jvm_memory_used_bytes'})
        
        result = (response.json()['data']['result'][0]['value'][0],
                  response.json()['data']['result'][0]['value'][1])
        
        time_data = date_conv(result[0])
        memory_used = size(int(result[1]), system=si)
        
        return (time_data, memory_used)

