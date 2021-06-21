import boto3
import boto3.session
from botocore.config import Config
from botocore.exceptions import ClientError
from SoundRecordCommand import credentials

class SoundUpload():
    def __init__(self):
        self.session = boto3.session.Session(
            aws_access_key_id=credentials.access_key_id,
            aws_secret_access_key=credentials.secret_access_key)
        self.client = self.session.client('s3', config=Config(
            region_name=credentials.region))
        
    def upload(self, file_name):
        file_substring = file_name.split("/")
        object_name = file_substring[len(file_substring)-1]
        try:
            response = self.client.upload_file(file_name, 'aws-verizon-project',
                                           object_name)
        except ClientError as e:
            print("Error while uploading")
        