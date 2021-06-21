import requests

class NewsRetrieval():
    def __init__(self, key):
        self.key = key
        
    def retrieve(self, topic):
        url = "https://newsapi.org/v2/everything?q="+topic+"&apiKey="+self.key
        info = requests.get(url)
        result = info.json()
        articles = result['articles']
        print(articles)
        for i in range(len(articles)):
            print("Title:")
            print(articles[i]['title'])
            print("Description:")
            print(articles[i]['description'])
            print("Url:")
            print(articles[i]['url'])
        return result