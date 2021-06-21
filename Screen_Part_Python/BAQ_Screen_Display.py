from NewsScreenDisplay.DynamoDBData import DDBRetrieve
from NewsScreenDisplay.NewsDisplay import Display

'''
def screen_display(url):
    app = QApplication(sys.argv)

    wid = QWidget()
    wid.resize(1000, 1000)

    news = QWebEngineView(wid)
    news.resize(1000, 1000)
    news.load(QUrl(url))
    news.show()

    wid.show()

    sys.exit(app.exec_())
'''
while True:
    news_retrieve = DDBRetrieve()
    news_url = news_retrieve.command_retrieve('AWS_Verizon_Commands')
    if news_url != "None":
        news_retrieve.delete_command('AWS_Verizon_Commands')
        news_display = Display()
        news_display.screen_display(news_url)









