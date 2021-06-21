from SoundRecordCommand import credentials
import boto3
import boto3.session
from botocore.exceptions import ClientError


class DDBOperation():
    def __init__(self):
        self.session = boto3.session.Session(aws_access_key_id = credentials.
                                             access_key_id, aws_secret_access_key=
                                             credentials.secret_access_key)
        self.client = self.session.resource('dynamodb', region_name =
                                                                credentials.region)
        
    def retrieve_command(self, table_name):
        try:
            table = self.client.Table(table_name)
            command = table.get_item(Key={'Device':'Mobile'})
            command_string = ""
            if command['Item']['Command'] == "Get":
                command_string = command['Item']['Command']+"#"+command['Item']['NewsInfo']
            elif command['Item']['Command'] == "Select":
                command_string = command['Item']['Command']+"#"+command['Item']['Url']
            elif command['Item']['Command'] == "Back":
                command_string = "Back"
            return command_string
        except ClientError:
            print('Error in retrieving items')
        except KeyError:
            return "None"

    def delete_command(self, table_name):
        try:
            table = self.client.Table(table_name)
            delete = table.delete_item(Key={'Device':'Mobile'})
        except ClientError:
            print('Error in deleting item')