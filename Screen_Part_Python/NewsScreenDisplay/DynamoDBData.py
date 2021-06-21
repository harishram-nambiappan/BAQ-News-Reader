import boto3
import boto3.session
from botocore.exceptions import ClientError
from NewsScreenDisplay import credentials


class DDBRetrieve():
    def __init__(self):
        self.session = boto3.session.Session(aws_access_key_id=credentials.access_key_id, aws_secret_access_key=credentials.secret_access_key)
        self.client = self.session.resource('dynamodb', region_name=credentials.region)

    def command_retrieve(self, table_name):
        try:
            table = self.client.Table(table_name)
            command = table.get_item(Key={'Device': 'Laptop'})
            display_command = command['Item']['Command']
            display_url = ""
            if display_command == 'Display':
                display_url = command['Item']['Url']
            return display_url
        except KeyError as e:
            return "None"

    def delete_command(self, table_name):
        try:
            table = self.client.Table(table_name)
            response = table.delete_item(Key={'Device': 'Laptop'})
        except ClientError as e:
            print("Error in deleting item")


