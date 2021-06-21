import boto3
import boto3.session
from botocore.exceptions import ClientError
from VoiceNewsRetrieval import credentials


class DDBDownload():
    def __init__(self):
        self.session = boto3.session.Session(aws_access_key_id=credentials.access_key_id, aws_secret_access_key=credentials.secret_access_key)
        self.client = self.session.resource('dynamodb', region_name=credentials.region)

    def download(self, table_name, key_name, key_value):
        try:
            table = self.client.Table(table_name)
            data = table.get_item(Key = {key_name: key_value})
            return data['Item']
        except ClientError as e:
            print(e) 
        
    
