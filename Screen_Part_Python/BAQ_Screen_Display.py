from NewsScreenDisplay.DynamoDBData import DDBRetrieve
from NewsScreenDisplay.NewsDisplay import Display

while True:
    news_retrieve = DDBRetrieve()
    news_url = news_retrieve.command_retrieve('AWS_Verizon_Commands')
    if news_url != "None":
        news_retrieve.delete_command('AWS_Verizon_Commands')
        news_display = Display()
        news_display.screen_display(news_url)









