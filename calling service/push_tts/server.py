# -*- coding: utf-8 -*-
from flask import Flask, Response, request, abort
import plivo, plivoxml

app = Flask(__name__)


r = plivoxml.Response()
emergency_info = {'info':"I have nonverbal autism, and I am speaking to you through an application on my phone."}


@app.route('/text-to-speech/', methods=['PUT'])
def make_new_xml():

	# Get data from post request  
    if not request.data:
    	return Response("No data was sent.")
    	#abort(400)

    emergency_info['info'] = request.data
    print "request.data is: ", request.data


    # Generate a Speak XML with the details of the text to play on the call.
    body1 = unicode(emergency_info['info'])
    print "Your message: ", body1
    params1 = {
        'language': "en-US",
        'voice': "MAN"
    }
    r.addSpeak(body1, **params1)
    return Response("successful update")





#Code here is only run when a post request is made to this server 
# The Te repeat/ "duplicate" lines may be due to the post vs put reequest
@app.route('/text-to-speech/', methods=['POST'])
def update_xml():

	# Get data from post request Must be JSON

	if not request.json or not 'info' in request.json:
		return Response("Either no data was sent or you have sent the wrong key/ json field.")
	 	#abort(400)

	emergency_info['info'] = request.json['info']
	print "request.data is: ", request.json['info']    



	# Generate a Speak XML with the details of the text to play on the call.
	body1 = unicode(emergency_info['info'])
	print "Your message: ", body1
	params1 = {
	    'language': "en-US",
	    'voice': "MAN"
	}
	r.addSpeak(body1, **params1)
	return Response("successful update. length of r is " )


@app.route('/text-to-speech/<info>', methods=['GET'])
def speak_xml(info):

	if info != None:
		emergency_info['info'] = info


	# Generate a Speak XML with the details of the text to play on the call.
	body1 = unicode(emergency_info['info'])
	print "The message received through the url: ", body1

	params1 = {
	    'language': "en-US", 
	    'voice': "MAN" 
	}
	r.addSpeak(body1, **params1)
	print r.to_xml()
	return Response(str(r), mimetype='text/xml')

if __name__ == "__main__":
    app.run(host='0.0.0.0', debug=True)