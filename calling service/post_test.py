import requests
url = 'https://ots-v1.herokuapp.com/text-to-speech/'
payload = {"info":"Whoah There."}


r = requests.post(url, json=payload)

print "r.text", r.text
print "r.status_code", r.status_code