import sys
from VoiceNewsRetrieval.news_retrieve import NewsRetrieval
from VoiceNewsRetrieval.dynamodb_upload import DDBEntry
from VoiceNewsRetrieval.dynamodb_download import DDBDownload
from VoiceNewsRetrieval.url_store import URLStore
from VoiceNewsRetrieval.s3_download import SoundDownload
import speech_recognition as sr
from rake_nltk import Rake
import nltk
from pydub import AudioSegment


def get_news(topic):
    key = "Your_NEWS_API_KEY"
    news = NewsRetrieval(key)
    data_entry = DDBEntry()
    results = news.retrieve(topic)
    news_info = ""
    for i in range(len(results)):
        data_entry.item_entry('AWS_Verizon_URL', results[i])
        if i == len(results)-1:
            news_info = news_info + results[i]['Title']
        else:
            news_info = news_info + results[i]['Title']+"/"
    device_command = {'Device': 'Mobile', 'Command': 'Get', 'NewsInfo': news_info, 'Url': 'None'}
    data_entry.item_entry('AWS_Verizon_Commands', device_command)

def select_news(number):
    news_entry = DDBDownload()
    news_info = news_entry.download("AWS_Verizon_URL", "S_No", number)
    data_entry = DDBEntry()
    device_command = {'Device': 'Mobile', 'Command': 'Select', 'NewsInfo': 'None', 'Url': news_info['URL']}
    data_entry.item_entry('AWS_Verizon_Commands', device_command)
    display_url = URLStore(news_info['URL'])
    return display_url

def shift_screen(device_name, display_url):
    shift_display = DDBEntry()
    shift_command = {'Device': device_name, 'Command': 'Display', 'NewsInfo': 'None', 'Url': display_url}
    shift_display.item_entry('AWS_Verizon_Commands', shift_command)


display_url = ""
while True:
    try:
        sound_file = SoundDownload()
        message = sound_file.download('instr.mp3')
        if message == "Success":
            filename = "instruction"
            sound = AudioSegment.from_mp3(filename + ".mp3")
            print(sound)
            sound.export("instruction.wav", format="wav")

            r = sr.Recognizer()

            AUDIO_FILE = "instruction.wav"

            nltk.download('stopwords')

            rake_nltk_var = Rake(min_length=1, max_length=5)

            r = sr.Recognizer()
            info = ""
            command = ""
            number = 0
       
            with sr.AudioFile(AUDIO_FILE) as source:
                audio = r.record(source)
                print("Transcription: " + r.recognize_google(audio))
                li = list(r.recognize_google(audio).split(" "))
                if li[0] == "get":
                    for i in range(4, len(li)):
                        info = info+" "+li[i]
                    print(info)
                    get_news(info)
                elif li[0] == "select":
                    if li[1] == "first":
                        number = 1
                    elif li[1] == "second":
                        number = 2
                    elif li[1] == "third":
                        number = 3
                    elif li[1] == "fourth":
                        number = 4
                    elif li[1] == "fifth":
                        number = 5
                    print(number)
                    display_url = select_news(number).geturl()
                elif li[0] == "display":
                    device = li[3]
                    print(device)
                    if device == "laptop":
                        shift_screen("Laptop", display_url)
                    elif device == "mobile":
                        shift_screen("Mobile", display_url)
                elif li[0] == "back":
                    data_entry = DDBEntry()
                    back_command = {'Device': 'Mobile', 'Command': 'Back', 'NewsInfo': 'None', 'Url': 'None'}
                    data_entry.item_entry('AWS_Verizon_Commands', back_command)
    except KeyboardInterrupt:
        break
