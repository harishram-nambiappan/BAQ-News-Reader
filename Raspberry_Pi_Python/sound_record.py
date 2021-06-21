import RPi.GPIO as GPIO
import time
import sounddevice as sd
from scipy.io.wavfile import write
from SoundRecordCommand.file_conversion import wav_mp3
from SoundRecordCommand.s3_upload import SoundUpload
import sys

channel = 17
GPIO.setmode(GPIO.BCM)
GPIO.setup(channel, GPIO.IN)

def record_callback(channel):
    if GPIO.input(channel):
        print("Recording")
        fs = 44100
        seconds = 7
        command = sd.rec(int(seconds*fs), samplerate=fs, channels=2)
        sd.wait()
        write('instr.wav', fs, command)
        command_file = wav_mp3()
        command_file.wav_mp3_convert('instr.wav', 'instr.mp3')
        command_upload = SoundUpload()
        command_upload.upload('instr.mp3')
        sys.exit("Command sent")
        
        
GPIO.add_event_detect(channel, GPIO.RISING, callback=record_callback, bouncetime=200)

while True:
    try:
        time.sleep(1)
    except KeyboardInterrupt:
        break