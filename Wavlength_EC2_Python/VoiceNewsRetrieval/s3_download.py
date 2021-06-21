import boto3
from botocore.exceptions import ClientError
from VoiceNewsRetrieval import credentials
from botocore.config import Config

class SoundDownload():
    def __init__(self):
        self.session = boto3.session.Session(aws_access_key_id=credentials.access_key_id, aws_secret_access_key=credentials.secret_access_key)
        self.resource  = self.session.resource('s3', config=Config(region_name=credentials.region))

    def download(self, object_name):
        try:
            self.resource.Bucket('aws-verizon-project').download_file(object_name, 'instruction.mp3')
            self.resource.Bucket('aws-verizon-project').delete_objects(Delete={'Objects':[{'Key': object_name}]})
            return "Success"
        except ClientError as e:
            return "File not present"
