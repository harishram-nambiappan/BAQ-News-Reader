import requests

class NewsRetrieval():
    def __init__(self, key):
        self.key = key
        
    def retrieve(self, topic):
        news_info = []
        url = "https://newsapi.org/v2/everything?q="+topic+"&apiKey="+self.key
        info = requests.get(url)
        result = info.json()
        articles = result['articles']
        for i in range(len(articles)):
            info = {'S_No':i+1,'Title':articles[i]['title'],'URL':articles[i]['url'],}
            news_info.append(info)         
        return news_info
