import plivo, plivoxml
from flask import Flask
import sys


info = sys.argv[1]


auth_id = "MANJI1ZGQYOWQ3NTU4MW"
auth_token = "NmU2YThiMmZiM2FlNDM1MDFkMTQ2NTAyMDJmNDdh"

p = plivo.RestAPI(auth_id, auth_token)

params = {
    'to': '14706293412',    # The phone numer to which the call will be placed // Arsh: 16784670532
    'from' : '11111111111', # The phone number to be used as the caller id

    # answer_url is the URL invoked by Plivo when the outbound call is answered
    # and contains instructions telling Plivo what to do with the call
    'answer_url' : "https://ots-v1.herokuapp.com/text-to-speech/" + info,
    'answer_method' : "GET", # The method used to call the answer_url

    # Example for asynchronous request
    # callback_url is the URL to which the API response is sent.
    #'callback_url' => "http://myvoiceapp.com/callback/",
    #'callback_method' => "GET" # The method used to notify the callback_url.
}
# Make an outbound call and print the response
response = p.make_call(params)
print str(response)

