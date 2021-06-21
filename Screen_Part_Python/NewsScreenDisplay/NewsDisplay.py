import sys
from PyQt5.QtWidgets import QApplication, QWidget, QLabel
from PyQt5.QtWebEngineWidgets import QWebEngineView
from PyQt5.QtCore import QUrl


class Display():
    def screen_display(self, url):
        app = QApplication(sys.argv)

        wid = QWidget()
        wid.resize(1500, 1500)

        news = QWebEngineView(wid)
        news.resize(1500, 1500)
        news.load(QUrl(url))
        news.show()

        wid.show()

        sys.exit(app.exec_())
