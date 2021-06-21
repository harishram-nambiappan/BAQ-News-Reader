from pydub import AudioSegment

class wav_mp3():
    def wav_mp3_convert(self, wav_file, mp3_file):
        audio = AudioSegment.from_wav(wav_file)
        audio.export(mp3_file, format="mp3")