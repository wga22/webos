function MainAssistant() {
	/* this is the creator function for your stage assistant object */
}

//var nMAXWidth = 80;
//var nMAXHeight = 30;
MainAssistant.fDebug = true;
MainAssistant.nMAXHeight = 15;
MainAssistant.nMAXWidth = 25;
MainAssistant.nBoardSize = 0;
MainAssistant.aColors = ['brown','green','red','blue', 'teal'];
MainAssistant.aaoMap = [];
MainAssistant.aZones = [];
MainAssistant.aSortedZonesNums = [];
MainAssistant.board = null;
MainAssistant.messageBox = null;
MainAssistant.borderStyle = "1px solid white";
MainAssistant.Players = [];
MainAssistant.firstClick = true;
MainAssistant.firstZone = null;
MainAssistant.prototype.setup = function() 
{
	/* this function is for setup tasks that have to happen when the stage is first created */
	
	var aRows = [];
	MainAssistant.nBoardSize = MainAssistant.nMAXWidth * MainAssistant.nMAXHeight;
	for (y=0; y < MainAssistant.nMAXHeight; y++)
	{
		var aRow = [];
		MainAssistant.aaoMap[y] = [];
		for(var x=0; x < MainAssistant.nMAXWidth; x++)
		{
			//aRow[x] = x + ":" + y;
			var sID = (x+'_'+y);
			aRow[x] = "<td id='"+sID+"'><img src=\"images/spacer.png\"/></td>" ;
			MainAssistant.aaoMap[y][x] = {x:x, y:y, id:sID, zone:-1, isOccupied:false};
		}
		aRows[y] = aRow.join(""); 
	}
	this.controller.get("board").innerHTML = "<tr>" + aRows.join("</tr><tr>") + "</tr>";
	//this.controller.get("board").border="1";
	
	//var oElements = this.controller.get("board").getElementsByTagName('td');
	MainAssistant.messageBox = this.controller.get("messages");
	//MainAssistant.board = this.controller.get("board");
	MainAssistant.board = this.controller;
	//MainAssistant.board = this;
	//MainAssistant.messageBox.update(oElements.length + " xxxx");
	if(!MainAssistant.fDebug)
	{
		MainAssistant.messageBox.style.display = 'none';
	}
	drawMaps();
	setupGame();
	Mojo.Event.listen(this.controller.get('board'), Mojo.Event.tap,	this.selectZone.bind(this));
	//Mojo.Event.listen(this.controller.get('bingoDiv'), Mojo.Event.tap,	this.referralBingo.bind(this));
};


MainAssistant.prototype.selectZone = function(event)
{
	var oZone = Zone.getZoneForClick(event);
	//var sColor = oZone.color;
	//writeMessage("tap: " + oZone.zone);
	
	if(oZone == null)
	{
		writeMessage("Please click again",true);
		return; //TODO, is this possible?
	}
	else if(MainAssistant.firstClick)
	{
		MainAssistant.firstClick = false;
		MainAssistant.firstZone = oZone;
		oZone.selected();
	}
	else if(MainAssistant.firstZone != null)  //second click
	{
		var oFirstZone = MainAssistant.firstZone;
		if(oFirstZone.isAdjacent(oZone))
		{
			oFirstZone.attack(oZone);
		}
		//unselect
		oFirstZone.unselected();
		MainAssistant.firstClick = true;
		MainAssistant.firstZone = null;
	}
}

function setupGame()
{
	//establish adjacent zones
	/*
	var atmpZones = MainAssistant.aZones;
	for(var x=0; x <atmpZones.length; x++ )
	{
		var oz =atmpZones[x]; 
		oz.setContent(x +":" + oz.listAdjacentZones().join(","));
	}
	*/
	//draw in draw borders
	
	//create players (amounts, randomly assign zones, put values on zones)
	createPlayers();
	//todo
	//pickFirstPlayer();
	//establishHuman()
	//while loop of turns across players, until only 1 player left!
	
}

function createPlayers()
{
	//MainAssistant.aColors = ['#ffffff', '#444444', '#003300', '#99339f']
	var nPlayers = MainAssistant.aColors.length;
	var nInitialDice= MainAssistant.aZones.length;
	var aZoneNums = MainAssistant.aSortedZonesNums.sort(function(x){return Math.random() > .5});
	var nZone = 0;
	var nZonesPerPlayer = Math.ceil(aZoneNums.length/nPlayers);
	for(var nPlayer = 0; nPlayer < nPlayers; nPlayer++)
	{
		var oPlayer = new Player();
		oPlayer.color = MainAssistant.aColors[nPlayer % MainAssistant.aColors.length];
		//assign zones to each player
		for(var x=0; x < nZonesPerPlayer && nZone < aZoneNums.length; x++, nZone++  )
		{
			var oZoneForPlayer = MainAssistant.aZones[aZoneNums[nZone]];
			oPlayer.zones.push(oZoneForPlayer);
			//oZoneForPlayer.setColor(oPlayer.color);
			oZoneForPlayer.setOwner(oPlayer);
		}
		//add dice to the player's zones
		oPlayer.distributeDiceToZones(nInitialDice);
		MainAssistant.Players[nPlayer] = oPlayer;
	}
}


function drawMaps()
{
	//determine how many to fill in
	var fxStager = false;
	var nColor = 0;
	var nZone = 0;
	//var aZones = [];
	//leave 4 blanks between each on a row
	//draw zones on every other row
	//TODO: handle same color for one zone
	for(var y=1; y < MainAssistant.nMAXHeight; y=y+2)
	{
		fxStager = !fxStager;	//rotate the start position
		//add a new block to each zone
		for(var x= (fxStager?1:3); x < MainAssistant.nMAXWidth; x=x+4 )
		{
			//MainAssistant.aaoMap[y][x].zone = nZone;
			//oMessageWindow.update(MainAssistant.aaoMap[y][x].id);
			//MainAssistant.board.get(MainAssistant.aaoMap[y][x].id).innerHTML = nZone;
			var oZone = new Zone();
			oZone.x = x;
			oZone.y = y;
			oZone.zone = nZone;
			MainAssistant.aZones[nZone] = oZone;
			MainAssistant.aSortedZonesNums[nZone] = nZone;
			occupyCell(MainAssistant.aaoMap[y][x], nZone);
			//TODO:
			//MainAssistant.aZones[nZone].adjacentZones.length;
			//MainAssistant.aZones[nZone].setColor()
			//aZones[nZone] = {x:x, y:y, zone:nZone};
			//aZones[nZone] = oZone;
			nZone++; //TODO
		}
	}
	nZone--;	//last one was never used
	//scan left
	var aSortedZones = MainAssistant.aSortedZonesNums.sort(function(elem){return Math.random()>.5});
	//fill in some vertical and horizontal lines
	for(var n=0; n < aSortedZones.length; n++)
	{
		var nTries = 6;
		var oRandomZone = MainAssistant.aZones[aSortedZones[n]]
		var zonex= oRandomZone.x;
		var zoney = oRandomZone.y; 
		while(nTries > 0)
		{
			var newX = zonex + plusMinus();
			var newY = zoney + plusMinus();
			if(validCell(newX, newY) && !MainAssistant.aaoMap[newY][newX].isOccupied)
			{
				occupyCell(MainAssistant.aaoMap[newY][newX], oRandomZone.zone);
				nTries--;
			}
			nTries--;
		}
	}
	
	//fillTheGaps(MainAssistant.aSortedZonesNums, function (x) {return --x}, function(y){return --y});
	//fillTheGaps(MainAssistant.aSortedZonesNums, function (x) {return ++x}, function(y){return ++y});
	//fillTheGaps(MainAssistant.aSortedZonesNums, function (x) {return --x}, function(y){return ++y});
	//fillTheGaps(MainAssistant.aSortedZonesNums, function (x) {return ++x}, function(y){return --y});
	drawBorders();
}

function plusMinus()
{
	var choices = [-1,0,1];
	return choices[Math.round(Math.random()*1000)%3];
}

function fillTheGaps(aZones, xMovementFunc, yMovementFunc)
{
	var aSortedZones = aZones.sort(function(elem){return Math.random()>.5});
	//fill in some vertical and horizontal lines
	//aSortedZones = aZones.sort(function(elem){return Math.random()>.5});
	
	for(var n=0; n < aSortedZones.length; n++)
	{
	
		var oRandomZone = MainAssistant.aZones[aSortedZones[n]]
		var tempZone =  oRandomZone.zone;
		var newX = oRandomZone.x;
		var newY = oRandomZone.y;
		while(validCell(newX, newY) && MainAssistant.aaoMap[newY][newX].zone == tempZone)
		{
			newX = xMovementFunc(newX);
			while( validCell(newX, newY) && !MainAssistant.aaoMap[newY][newX].isOccupied)
			{
				occupyCell(MainAssistant.aaoMap[newY][newX], tempZone);
				newY = yMovementFunc(newY);
			}
		}
	}
}


function drawBorders()
{
	//left to right for each row
	var nMaxWidth = MainAssistant.nMAXWidth;
	for(var y=0; y < MainAssistant.nMAXHeight; y++)
	{
		var tmpZone = -1;
		for(var x=0; x < nMaxWidth ; x++)
		{
			if(MainAssistant.aaoMap[y][x].zone!=tmpZone)
			{
				//the cell is unowned, so own it, then draw the border
				while(validCell(x,y) && MainAssistant.aaoMap[y][x].zone == -1 && tmpZone!=-1)
				{
					occupyCell(MainAssistant.aaoMap[y][x], tmpZone);
					x++;
				}
				if( x < nMaxWidth)
				{
					//draw the border
					MainAssistant.board.get(MainAssistant.aaoMap[y][x].id).style.borderLeft=MainAssistant.borderStyle;
					//tell zone its neighbor, and vice versa (redundant?)
					if(MainAssistant.aaoMap[y][x].zone != -1 && tmpZone !=-1)
					{
						//MainAssistant.aZones[MainAssistant.aaoMap[y][x].zone].adjacentZones;
						MainAssistant.aZones[MainAssistant.aaoMap[y][x].zone].adjacentZones[tmpZone] = true;
						MainAssistant.aZones[tmpZone].adjacentZones[MainAssistant.aaoMap[y][x].zone] = true;
						//writeMessage(tmpZone)
					}
					tmpZone=MainAssistant.aaoMap[y][x].zone;
				}
			}
		}
	}
	//right to left of each row
	for(var y=0; y < MainAssistant.nMAXHeight; y++)
	{
		var tmpZone = -1;
		for(var x=nMaxWidth-1; x >=0 ; x--)
		{
			if(MainAssistant.aaoMap[y][x].zone!=tmpZone)
			{
				MainAssistant.board.get(MainAssistant.aaoMap[y][x].id).style.borderRight=MainAssistant.borderStyle;
				if(MainAssistant.aaoMap[y][x].zone != -1 && tmpZone !=-1)
				{
					//MainAssistant.aZones[MainAssistant.aaoMap[y][x].zone].adjacentZones;
					MainAssistant.aZones[MainAssistant.aaoMap[y][x].zone].adjacentZones[tmpZone] = true;
					MainAssistant.aZones[tmpZone].adjacentZones[MainAssistant.aaoMap[y][x].zone] = true;
					//writeMessage(tmpZone)
				}
				tmpZone=MainAssistant.aaoMap[y][x].zone;
			}
		}
	}

	//south to north for each column
	for(var x=MainAssistant.nMAXWidth-1; x >=0 ; x--)
	{
		var tmpZone = -1;
		for(var y=0; y < MainAssistant.nMAXHeight; y++)
		{
			if(MainAssistant.aaoMap[y][x].zone!=tmpZone)
			{
				MainAssistant.board.get(MainAssistant.aaoMap[y][x].id).style.borderTop=MainAssistant.borderStyle;
				if(MainAssistant.aaoMap[y][x].zone != -1 && tmpZone !=-1)
				{
					//MainAssistant.aZones[MainAssistant.aaoMap[y][x].zone].adjacentZones;
					MainAssistant.aZones[MainAssistant.aaoMap[y][x].zone].adjacentZones[tmpZone] = true;
					MainAssistant.aZones[tmpZone].adjacentZones[MainAssistant.aaoMap[y][x].zone] = true;
					//writeMessage(tmpZone)
				}
				tmpZone=MainAssistant.aaoMap[y][x].zone;
			}
		}
	}

	//north to south for each column
	for(var x=MainAssistant.nMAXWidth-1; x >=0 ; x--)
	{
		var tmpZone = -1;
		for(var y=MainAssistant.nMAXHeight-1; y >=0 ; y--)
		{
			if(MainAssistant.aaoMap[y][x].zone!=tmpZone)
			{
				MainAssistant.board.get(MainAssistant.aaoMap[y][x].id).style.borderBottom=MainAssistant.borderStyle;
				if(MainAssistant.aaoMap[y][x].zone != -1 && tmpZone !=-1)
				{
					//MainAssistant.aZones[MainAssistant.aaoMap[y][x].zone].adjacentZones;
					MainAssistant.aZones[MainAssistant.aaoMap[y][x].zone].adjacentZones[tmpZone] = true;
					MainAssistant.aZones[tmpZone].adjacentZones[MainAssistant.aaoMap[y][x].zone] = true;
					//writeMessage(tmpZone)
				}
				tmpZone=MainAssistant.aaoMap[y][x].zone;
			}
		}
	}	
}


function validCell(x,y)
{
	return !(x<0  || y < 0 || x>=MainAssistant.nMAXWidth || y>=MainAssistant.nMAXHeight)
}

function occupyCell(oCell, nZone)
{
	if(!oCell.isOccupied)
	{
		oCell.zone = nZone;
		var oDom = MainAssistant.board.get(oCell.id);
		var sOwner = MainAssistant.aColors[nZone%MainAssistant.aColors.length];
		oDom.style.backgroundColor = sOwner;
		//oCell.owner = sOwner;
		MainAssistant.aZones[nZone].cells.push(oCell)
		if(MainAssistant.fDebug)
		{
		//	oDom.innerHTML = nZone + "[" + oCell.id +"]";	
		}
		oCell.isOccupied = true;
	}
}


function writeMessage(sMessage, fClear)
{
	var sAppend = fClear ? "" : MainAssistant.messageBox.innerHTML; 
	MainAssistant.messageBox.innerHTML = ((sMessage) + "<br>" + sAppend);
}




MainAssistant.prototype.activate = function(event) {
	/* put in event handlers here that should only be in effect when this scene is active. For
	   example, key handlers that are observing the document */
};

MainAssistant.prototype.deactivate = function(event) {
	/* remove any event handlers you added in activate and do any other cleanup that should happen before
	   this scene is popped or another scene is pushed on top */
};

MainAssistant.prototype.cleanup = function(event) {
	/* this function should do any cleanup needed before the scene is destroyed as 
	   a result of being popped off the scene stack */
};
