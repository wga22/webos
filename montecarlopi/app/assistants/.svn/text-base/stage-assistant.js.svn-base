function StageAssistant() {
	/* this is the creator function for your stage assistant object */
}

StageAssistant.dom = null;
StageAssistant.RADIUS = 370;
StageAssistant.RADSQR = 0;
//StageAssistant.MAXWIDTH = 760;
StageAssistant.nCountInside = 0;
StageAssistant.nCount = 0;
StageAssistant.lastVals = "Last Acceleration Values";
StageAssistant.drawing=null;
StageAssistant.LINEHEIGHT = 20;
StageAssistant.TEXTBOX_X = (StageAssistant.RADIUS * 2);
StageAssistant.TESTING = false;
StageAssistant.AMAZON = {x:0, y:0};


StageAssistant.prototype.setup = function() {
	/* this function is for setup tasks that have to happen when the stage is first created */
	
	//setup items
	this.handleAcceleration = this.handleAcceleration.bindAsEventListener(this);
	Mojo.Event.listen(document, 'acceleration', this.handleAcceleration);
	//30hz
	this.controller.setWindowProperties({fastAccelerometer:true});
	//Mojo.Event.listen(this.controller.get('bingoDiv'), Mojo.Event.tap,	this.referralBingo.bind(this));
	Mojo.Event.listen(this.controller.get('canvas'), Mojo.Event.tap,this.taps.bind(this));
	
	//setup drawing
	StageAssistant.dom=this;
	var canvas = StageAssistant.dom.controller.get("canvas");
	StageAssistant.drawing = canvas.getContext('2d');
	//c.strokeText( "SWEET!"  , RADIUS+RADIUS+5, 100, 100 );
	//c.fillStyle = "red";
	//c.fillRect(100,100,400,300);
	StageAssistant.RADSQR = StageAssistant.RADIUS * StageAssistant.RADIUS; 
	
	//draw circle
	//StageAssistant.drawing.arc(StageAssistant.RADIUS,StageAssistant.RADIUS,StageAssistant.RADIUS,0,(2*Math.PI),false);
	//StageAssistant.drawing.stroke();

	//generate labels
	var asTitle = ["Calculation of Pi",  "Using the Monte Carlo Method"];
	var asSubTitle =["Pi can be approximated based ", "on known formulas of","the area of a circle ","and area of a square",
	                 "pi = (4*H)/T", "Touch circle for updated approximation", "based on random",
	                 "points within the square.", "Shake the TouchPad to speed up!"];	
	
	//draw black box on side
	StageAssistant.drawing.fillStyle = "black";
	StageAssistant.drawing.fillRect(StageAssistant.TEXTBOX_X,0,300,(StageAssistant.RADIUS * 2));	//offset to account for text height

	//set font size and color
	StageAssistant.drawing.fillStyle = "red";
	StageAssistant.drawing.font = "14pt Arial";

	//start writing text at standard position
	var xLoc = StageAssistant.TEXTBOX_X + 5; // give some padding
	var yLoc = StageAssistant.LINEHEIGHT;

	
	//write title
	writeLines(StageAssistant.drawing, asTitle, xLoc, yLoc);
	StageAssistant.drawing.font = "10pt Arial";
	
	//move pen to new location
	yLoc+= 5 * StageAssistant.LINEHEIGHT;
	xLoc = StageAssistant.TEXTBOX_X + 15;
	
	//write subtitle
	writeLines(StageAssistant.drawing, asSubTitle, xLoc, yLoc);
	
	//StageAssistant.drawing.strokeText( sTitle ,xLoc ,yLoc+(StageAssistant.LINEHEIGHT) , 100 );

	//amazon ad
	drawAmazonAd(StageAssistant.drawing);
	
	//draw PI
	drawPi(StageAssistant.drawing);
	
	//finish by setting font back to our standard size.
	StageAssistant.drawing.font = "10pt Arial";
	
	//setup menu
	//createMenu(this.controller);
};

function createMenu(cntr)
{

	this.appMenuModel = {
			  items: [
			      {label: "About My App...", command: 'do-myAbout', shortcut: 'a'}
			  ]
			};

	//cntr.setupWidget(Mojo.Menu.appMenu, {}, this.appMenuModel);
	
	return
	cntr.setupWidget(Mojo.Menu.appMenu,
			this.attributes = {
			  omitDefaultItems: true
			},
			this.model = {
			  visible: true,
			  items: [
			      {label: "About My App ...", command: 'do-myAbout'},
			      Mojo.Menu.editItem,
			      { label: "Preferences...", command: 'do-myPrefs' },
			      { label: "Help...", command: 'do-myHelp' }
			  ]
			}); 
}

function drawAmazonAd(oDraw)
{
	var img = new Image();
	img.src =  Mojo.appPath + '/images/amazon.jpg'
	StageAssistant.AMAZON = {x:StageAssistant.TEXTBOX_X, y: ((StageAssistant.RADIUS*2)-img.height)};
	oDraw.drawImage(img, StageAssistant.AMAZON.x, StageAssistant.AMAZON.y);
}

function drawPi(oDraw)
{
	var img = new Image();
	img.src =  Mojo.appPath + '/images/pi-symbol.png'
	//StageAssistant.AMAZON = {x:StageAssistant.TEXTBOX_X, y: ((StageAssistant.RADIUS*2)-img.height)};
	//position the image in middle, subtract image dimension from the middle
	oDraw.drawImage(img, (StageAssistant.RADIUS-Math.round(img.width/2)), (StageAssistant.RADIUS-Math.round(img.height/2)));
}



function drawspots(event) 
{
	//var nShake1 = Math.abs(event.accelZ) + Math.abs(event.accelY) + Math.abs(event.accelX);
	//in test mode, run 1000 times, otherwise just once per accel event
	//for(var n = (StageAssistant.TESTING ? 1000 : 10); n>0; n--)
	
	//see how hard its being shaken
	var nAmplifier = Math.abs(event.accelX) + Math.abs(event.accelZ) + Math.abs(event.accelY)
	
	//convert the shaking into a value from 10-100, for number of dots per 30hz (30 X 100 = 3000 dots per second max)
	var nShakes = Math.round(10 * nAmplifier);
	nShakes = Math.max(Math.min(nShakes, 200), 10);

	if(StageAssistant.TESTING)
	{
		Mojo.Log.info("drawspots amp: " + nShakes + " nShakes:" + nShakes);	
		//Mojo.Log.info("x = " + event.accelX +", y = " +  event.accelY +", z = " +  event.accelZ;);
		nShakes+=1000;
	}

	
	
	for(var n = nShakes; n>0; n--)
	{
		//var x= randomSpot(StageAssistant.RADIUS, true)+StageAssistant.RADIUS;
		//use Z 5th digit to get negative
		var x = MonteCarlo.randomSpot(StageAssistant.RADIUS);
		var xPos = x + +StageAssistant.RADIUS;
		//var y= randomSpot(StageAssistant.RADIUS, true)+StageAssistant.RADIUS;
		
		//use Z 5th digit to get negative direction
		var y = MonteCarlo.randomSpot(StageAssistant.RADIUS)
		var yPos = y + +StageAssistant.RADIUS;

		StageAssistant.nCount++;
		//if inside, under r^2
		if(((x*x)+(y*y)) <= StageAssistant.RADSQR)
		{
			StageAssistant.drawing.fillStyle = "green";
			StageAssistant.drawing.fillRect(xPos,yPos,1,1);
			StageAssistant.nCountInside++;
			//StageAssistant.drawing.fillStyle = "black";
		}
		else
		{
			StageAssistant.drawing.fillStyle = "red";
			StageAssistant.drawing.fillRect(xPos,yPos,1,1);			
		}
	}
	
}

function screenTouch(event)
{
	var x= event.down.x;
	var y = event.down.y;
	if(y > StageAssistant.AMAZON.y && x > StageAssistant.AMAZON.x )
	{
		goToAmazon(this, 'pi');
	}
	else
	{
		drawPi(StageAssistant.drawing);
		writeScore(event);
	}
	
}

function writeScore(event)
{
	if(StageAssistant.nCount == 0) return; //prevent divide by 0
	var xLoc = StageAssistant.TEXTBOX_X;
	var yLoc = 300;
	
	var aLines = MonteCarlo.getRowsOfPiDetails(StageAssistant.nCount, StageAssistant.nCountInside)
	
	StageAssistant.drawing.fillStyle = "black";
	StageAssistant.drawing.fillRect(xLoc,yLoc-StageAssistant.LINEHEIGHT,300,(StageAssistant.AMAZON.y-yLoc-StageAssistant.LINEHEIGHT));	//offset to account for text height
	StageAssistant.drawing.fillStyle = "red";
	//put the text INSIDE the box
	xLoc = StageAssistant.TEXTBOX_X + 15;
	yLoc += StageAssistant.LINEHEIGHT;
	writeLines(StageAssistant.drawing, aLines, xLoc, yLoc)
}

function writeLines(oDraw, aLines, xLoc, yLoc)
{
	for(var n=0; n < aLines.length; n++)
	{
		oDraw.strokeText( aLines[n]  ,xLoc ,yLoc+(StageAssistant.LINEHEIGHT*n) , 100 );
	}
}

function convertAccelValues(nMaxVal,nLength,nPosAndNeg)
{
	nLength = nLength==0 ? Math.random() : nLength;
	//var tens = Math.round((Math.random() * 1000000)/100000)+1;
	var fPosOrNeg = Math.random() > 0.5;
	
	//shift to a new spot in the results
	var tens = Math.ceil(Math.random()*8);
	var nDigit = Math.pow(10,tens);
	var nVal = Math.round((nLength  * nDigit * Math.random() ))  % nMaxVal;
	Mojo.Log.info("tens: " + nDigit + " - val " + nVal);
	return fPosOrNeg ? -nVal : nVal; 
}


function OLDconvertAccelValues(nMaxVal,nLength,nPosAndNeg)
{
	//TODO: remove for prod
	nLength = nLength==0 ? Math.random() : nLength;
	
	var fPosOrNeg = Math.random() > 0.5;
	var nVal = (nLength  * 100000)  % nMaxVal;
	return fPosOrNeg ? -nVal : nVal; 
}

function convertAccelValuesNEW(nMaxVal,nLength,nPosAndNeg)
{
	nLength = nLength==0 ? Math.random() : nLength;
	
	var sNumber = (nLength + "").replace(".", "");
	var aNumber = sNumber.split("0");
	sNumber = aNumber.sort(function(z){return ((Math.random() > 0.5) ? 1: -1) }).join("9");
	//(nPosAndNeg > 0) ? (Math.random()*33333) : nPosAndNeg;
	var fPosOrNeg = Math.random() > 0.5;
	var tens = Math.ceil(Math.random() * 10);
	var nVal = Math.round(parseInt(sNumber)/(Math.exp(10, tens)))  % nMaxVal;
	Mojo.Log.info("SNUM: " + sNumber + " - " + nVal); 
	return fPosOrNeg ? -nVal : nVal; 
}



function goToAmazon(a_oDom, sName)
{
	var urls = {pi: "https://www.amazon.com/dp/B003TU7PPQ/ref=as_li_ss_til?tag=willalledeve-20&camp=0&creative=0&linkCode=as4&creativeASIN=B003TU7PPQ&adid=0ZC691MTS3VZAVAB0J54&" };
	
	//undef: a_oDom.parent, this.controller, a_oDom.controller.serviceRequest
	//writeLines(StageAssistant.drawing, [this, a_oDom, a_oDom.controller,a_oDom.controller.serviceRequest  ], 100, 100);
	//return
	
	//this.controller.serviceRequest("palm://com.palm.applicationManager", {
	
	var oProps = {
		        method: "open",
		        activityId: 122311,
		        parameters:  {
		           id: 'com.palm.app.browser',
		           params: {
		              //target: urls["eightball"]
		        	   //target: urls.pi
		        	   target: urls[sName]
		           }
		        }
	};
	
	var request = new Mojo.Service.Request("palm://com.palm.applicationManager", oProps);
	
	
	/*
	Mojo.controller.serviceRequest("palm://com.palm.applicationManager", {
        method: "open",
        activityId: 122311,
        parameters:  {
           id: 'com.palm.app.browser',
           params: {
              //target: urls["eightball"]
        	   //target: urls.pi
        	   target: urls[sName]
           }
        }
    });
    */

}

StageAssistant.prototype.cleanup = function(event) {
	this.controller.setWindowProperties({fastAccelerometer:false});
	//Mojo.Event.stopListening(document, 'orientationchange',this.handleOrientation);
	Mojo.Event.stopListening(document, 'acceleration', this.handleAcceleration);
	//Mojo.Event.stopListening(document, 'acceleration', this.handleButtonPress);
};

StageAssistant.prototype.handleAcceleration = drawspots;
StageAssistant.prototype.taps = screenTouch;
