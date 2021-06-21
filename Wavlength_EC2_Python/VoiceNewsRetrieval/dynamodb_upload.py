import boto3
import boto3.session
from botocore.exceptions import ClientError
from botocore.config import Config
from VoiceNewsRetrieval import credentials

class DDBEntry():
    def __init__(self):
        self.session = boto3.session.Session(aws_access_key_id=credentials.access_key_id, aws_secret_access_key=credentials.secret_access_key)
        self.client = self.session.resource('dynamodb', config=Config(region_name=credentials.region))
        
    def item_entry(self, table, item):
        self.table = self.client.Table(table)
        self.table.put_item(Item=item)

    
