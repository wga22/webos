/*
 * Debugging hints:  
 * to get logging: (https://developer.palm.com/content/api/dev-guide/tools/emulator.html)
 * 1) from putty, go to localhost 5722, root/<blank>
 * 2) run "log com.willallen.eightball"
 * 
 */

function MainAssistant() {
}


var fDebug = false;
MainAssistant.fButton = false;
MainAssistant.dom = null;
MainAssistant.asAnswers = [];
MainAssistant.oSeeds ={x:null, y:null, z:null};
MainAssistant.fdiceSpinning = true;
MainAssistant.bingo = null;

function getDimension(anNum)
{
	return anNum ? (Math.round(Math.abs(anNum)*5000 * Math.random())) : Math.round(10000 * Math.random());
}

function getSingleInt(aoSeed)
{
	return getDimension(aoSeed.x) + getDimension(aoSeed.y) + getDimension(aoSeed.z);
}

function getAnswer() 
{
	var sAnswer = "";
	var nRandVal = getSingleInt(MainAssistant.oSeeds) % (MainAssistant.asAnswers.length);
	MainAssistant.dom.controller.get("eightballDiv").update(MainAssistant.asAnswers[nRandVal]);
	MainAssistant.dom.controller.get("spinner1").mojo.stop();
}

function spinDice()
{
	if(MainAssistant.fdiceSpinning)
	{
		var one = dieVal(Math.random());
		var two = dieVal(Math.random());
		MainAssistant.dom.controller.get("die1").src = 'images/dice_'+one+'.png';
		MainAssistant.dom.controller.get("die2").src = 'images/dice_'+two+'.png';
		setTimeout("spinDice()",320);
	}
}

function dieVal(nInput)
{
	return (getDimension(nInput) % 6) +1;
}

function setDice()
{
	var one = dieVal(MainAssistant.oSeeds.x);
	var two = dieVal(MainAssistant.oSeeds.y);
	MainAssistant.dom.controller.get("die1").src = 'images/dice_'+one+'.png';
	MainAssistant.dom.controller.get("die2").src = 'images/dice_'+two+'.png';
	MainAssistant.fdiceSpinning = false;
	//writeDebug("setdice");
}

function writeDebug(sVal)
{
	if(fDebug) MainAssistant.dom.controller.get("debug").update( 
			"<br/> shake seeds x:" + MainAssistant.oSeeds.x + " y:" + MainAssistant.oSeeds.y +" z: " + MainAssistant.oSeeds.z  
			+ "<br/> fspinning:" + MainAssistant.fdiceSpinning
			+ "<br/> val "  + sVal);
}

MainAssistant.prototype.handleAcceleration = function(event) 
{
	var nShake1 = Math.abs(event.accelZ) + Math.abs(event.accelY) + Math.abs(event.accelX);
	if (MainAssistant.fButton) 
	{
		MainAssistant.fdiceSpinning = true;
		MainAssistant.fButton = false;
		MainAssistant.oSeeds.x = event.accelX;
		MainAssistant.oSeeds.y = event.accelY;
		MainAssistant.oSeeds.z = event.accelZ;
		this.controller.get("spinner1").mojo.start();	
		MainAssistant.dom=this;
		this.controller.get("eightballDiv").update("Working on answer...");
		//writeDebug("handleAcceleration");
		setTimeout("getAnswer()", 3300);
		spinDice();
		setTimeout("setDice()", 2200);
		//getAnswer(this, nShake1);
		doBingoPull();
		setTimeout("doLotteryBalls()", 1100);
	}
}

MainAssistant.prototype.handleButtonPress = function(event) 
{
	if((event.target.id + '').toLowerCase().indexOf('bingo')>-1)
	{
		setBingo(this);
	}
	/*
	else if((event.target.id + '').toLowerCase().indexOf('ball')>-1)
	{
		this.controller.get("eightballDiv").update("going to Amazon");
		goToAmazon(this, "eightball");
	}
	*/
	else
	{
		MainAssistant.fButton = true;
		MainAssistant.fdiceSpinning = true;
		//this.mediaObj.play();
		this.diceRollSound.play();
	}
}

/*
 * MainAssistant.prototype.handleOrientation = function(event){ var position = [
 * "Flat, face up", "Flat, face down", "Upright", "Upside Down", "Pointed left",
 * "Pointed right"];
 * 
 * //this.controller.get("position").update("Orientation: " +
 * position[event.position]); //this.controller.get("roll").update("Roll: " +
 * event.roll + " degrees"); //this.controller.get("pitch").update("Pitch: " +
 * event.pitch + " degrees"); }
 * 
 */

MainAssistant.prototype.setup = function() 
{
	setVars();
	if (this.controller.stageController.setWindowOrientation) 
	{
		this.controller.stageController.setWindowOrientation("free");
		this.controller.stageController.setWindowProperties({
			fastAccelerometer : false
		});
	}
	this.controller.setupWidget("spinner1",
			  this.attributes = {
			      spinnerSize: "large"
			  },
			  this.model = {
			      spinning: false
			  }
			); 	
	this.controller.setupWidget("askButton", this.attributes = {},
			this.model = {
				label : "Press Your Luck!",
				disabled : false
			});
	this.controller.setupWidget("resetBingo", this.attributes = {id:"resetBingo"},
			this.model = {
				label : "Reset Bingo Game",
				disabled : false
			});
	// this.handleOrientation =
	// this.handleOrientation.bindAsEventListener(this);
	// Mojo.Event.listen(document, 'orientationchange', this.handleOrientation);
	this.handleAcceleration = this.handleAcceleration.bindAsEventListener(this);
	Mojo.Event.listen(document, 'acceleration', this.handleAcceleration);
	Mojo.Event.listen(this.controller.get('askButton'), Mojo.Event.tap,	this.handleButtonPress.bind(this));
	Mojo.Event.listen(this.controller.get('resetBingo'), Mojo.Event.tap,	this.handleButtonPress.bind(this));
	Mojo.Event.listen(this.controller.get('eightballpic'), Mojo.Event.tap,	this.referralEightball.bind(this));
	Mojo.Event.listen(this.controller.get('die1'), Mojo.Event.tap,	this.referralDice.bind(this));
	Mojo.Event.listen(this.controller.get('die2'), Mojo.Event.tap,	this.referralDice.bind(this));
	Mojo.Event.listen(this.controller.get('lotteryDiv'), Mojo.Event.tap,	this.referralLottery.bind(this));
	Mojo.Event.listen(this.controller.get('bingoDiv'), Mojo.Event.tap,	this.referralBingo.bind(this));
	//document.body.style.height = '1024';
	//document.body.style.width = '1280';
	//document.body.style.backgroundImage = 'EightBall/images/magic-bus-wallpapers_7855_1280x1024.jpg';
	//document.body.style.backgroundImage = url('http://www.google.com/logos/classicplus.png');
	//var oAnswer = this.controller.get("answer");
	// Load the MediaExtension library

	//set up sounds
	//https://developer.palm.com/content/api/reference/services/audio.html
	//this.libs = MojoLoader.require({ name: "mediaextension", version: "1.0"});
	//this.mediaObj = this.controller.get("myMediaElement");
	//this.extObj = this.libs.mediaextension.MediaExtension.getInstance(this.mediaObj);
	//this.extObj.audioClass = "media";
	this.diceRollSound = new Audio();
	this.diceRollSound.src = Mojo.appPath + '/images/dice.mp3';
	
};

MainAssistant.prototype.referralEightball = function(event) 
{
	goToAmazon(this, "eightball")
};

MainAssistant.prototype.referralDice = function(event) 
{
	goToAmazon(this, "dice")
};
MainAssistant.prototype.referralLottery = function(event) 
{
	goToAmazon(this, "lottery")
};
MainAssistant.prototype.referralBingo = function(event) 
{
	goToAmazon(this, "bingo")
};



MainAssistant.prototype.cleanup = function(event) {
	//Mojo.Event.stopListening(document, 'orientationchange',this.handleOrientation);
	//Mojo.Event.stopListening(document, 'acceleration', this.handleAcceleration);
	//Mojo.Event.stopListening(document, 'acceleration', this.handleButtonPress);
};

function setVars() 
{
	MainAssistant.dom = this;
	MainAssistant.asAnswers = [ 'It is certain', 'It is decidedly so', 'Without a doubt',
			'Yes, definitely', 'You may rely on it', 'As I see it, yes',
			'Most likely', 'Outlook good', 'Signs point to yes', 'Yes',
			'Reply hazy, try again', 'Ask again later',
			'Better not tell you now', 'Cannot predict now',
			'Concentrate and ask again', 'Don\'t count on it',
			'My reply is no', 'My sources say no', 'Outlook not so good',
			'Very doubtful' ];
	setBingo();
	
}

function setBingo(dom)
{
	var aBalls = [];
	var aLetters = ['B', 'I', 'N','G', 'O'];
	var fDom = false;
	if(dom != null)
	{
		dom.controller.get("bingoHeader").innerHTML = "";
		fDom = true;
	}	
	for(var i=0; i < aLetters.length; i++ )
	{
		if(fDom) dom.controller.get("bingo_"+i).innerHTML = "";
		for(var x=1; x <= 15; x++)
		{
			var nBall = (x + (i*15));
			aBalls.push({letterid: i, number: nBall, label:(aLetters[i] + '' + nBall)});
		}
	}
	MainAssistant.bingo = aBalls;
}



function doBingoPull()
{
	var nBall = getSingleInt(MainAssistant.oSeeds) % MainAssistant.bingo.length;
	var nLetterID = MainAssistant.bingo[nBall].letterid;
	//writeDebug("letter: " + nLetterID + "" + MainAssistant.bingo[nBall].label);
	MainAssistant.dom.controller.get("bingoHeader").innerHTML = (MainAssistant.bingo[nBall].label);
	if(MainAssistant.dom.controller.get("bingo_" + nLetterID).innerHTML.length >= 2)
	{
		MainAssistant.dom.controller.get("bingo_" + nLetterID).innerHTML += ", ";
	}
	MainAssistant.dom.controller.get("bingo_" + nLetterID).innerHTML += (MainAssistant.bingo[nBall].label);
	MainAssistant.bingo.splice(nBall,1);
	//this.controller.showAlertDialog( nLetter + "" + MainAssistant.bingo[nLetter]	);
	
}

function doLotteryBalls()
{
	//var sInit = MainAssistant.dom.controller.get("lbdiv1").top;
	var nMaxMain = 56
	var nMaxMega = 46;
	for(var x = 1; x <= 6; x++)
	{
		//MainAssistant.dom.controller.get("lbdiv" + x).top = MainAssistant.dom.controller.get("lb" + x).style.top
		//MainAssistant.dom.controller.get("lbdiv" + x).left =MainAssistant.dom.controller.get("lb" + x).style.left;
		//MainAssistant.dom.controller.get("lbdiv" + x).top = '10px';
		//MainAssistant.dom.controller.get("lbdiv" + x).left = (x * 20) + "px";
		MainAssistant.dom.controller.get("lbd" + x).innerHTML = ((getDimension(null) % nMaxMain) + 1);
	}
	MainAssistant.dom.controller.get("lbd7").innerHTML = ((getDimension(null) % nMaxMega) + 1);
	//MainAssistant.dom.controller.get("lbd").innerHTML = sInit;
	//writeDebug("lottery: " + sInit);
}

function doCandyLand()
{
	//(6 single color, 6 double color, 6 character),
	//red, purple, yellow, blue, orange,green
	//candy cane, ice cream, cake
}


function goToAmazonEightBall(a_oDom)
{
	goToAmazon(a_oDom, "eightball")
}

function goToAmazon(a_oDom, sName)
{
	var urls = {eightball: "http://www.amazon.com/gp/product/B00001ZWV7/ref=as_li_qf_sp_asin_tl?ie=UTF8&tag=willalledeve-20&linkCode=as2&camp=217145&creative=399377&creativeASIN=B00001ZWV7", 
			bingo:"http://www.amazon.com/gp/product/B0016HN3GO/ref=as_li_ss_tl?ie=UTF8&tag=willalledeve-20&linkCode=as2&camp=217145&creative=399369&creativeASIN=B0016HN3GO",	
			dice:"http://www.amazon.com/gp/product/B00000IWH6/ref=as_li_ss_tl?ie=UTF8&tag=willalledeve-20&linkCode=as2&camp=217145&creative=399369&creativeASIN=B00000IWH6", 
			lottery:"http://www.amazon.com/gp/product/1463797591/ref=as_li_ss_tl?ie=UTF8&tag=willalledeve-20&linkCode=as2&camp=217145&creative=399373&creativeASIN=1463797591"
				};
	a_oDom.controller.serviceRequest("palm://com.palm.applicationManager", {
        method: "open",
        activityId: 122311,
        parameters:  {
           id: 'com.palm.app.browser',
           params: {
              //target: urls["eightball"]
        	   target: urls[sName]
           }
        }
    });

}

