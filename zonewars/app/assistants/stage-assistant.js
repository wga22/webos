// {"scenes": "stage","source": "app/assistants/stage-assistant.js"}
function StageAssistant() {
	/* this is the creator function for your stage assistant object */
}

//var nMAXWidth = 80;
//var nMAXHeight = 30;
StageAssistant.fDebug = true;
StageAssistant.nMAXHeight = 15;
StageAssistant.nMAXWidth = 25;
StageAssistant.nBoardSize = 0;
StageAssistant.aColors = ['brown','green','red','blue','teal', '#553322', 'yellow'];
StageAssistant.aaoMap = [];
StageAssistant.aZones = [];
StageAssistant.board = null;
StageAssistant.messageBox = null;
StageAssistant.borderStyle = "1px solid white";
StageAssistant.Players = [];
StageAssistant.prototype.setup = function() {
	/* this function is for setup tasks that have to happen when the stage is first created */
	
	var aRows = [];
	StageAssistant.nBoardSize = StageAssistant.nMAXWidth * StageAssistant.nMAXHeight;
	for (y=0; y < StageAssistant.nMAXHeight; y++)
	{
		var aRow = [];
		StageAssistant.aaoMap[y] = [];
		for(var x=0; x < StageAssistant.nMAXWidth; x++)
		{
			//aRow[x] = x + ":" + y;
			var sID = (x+'_'+y);
			aRow[x] = "<td id='"+sID+"'><img src=\"images/spacer.png\"/></td>" ;
			StageAssistant.aaoMap[y][x] = {x:x, y:y, id:sID, zone:-1, isOccupied:false};
		}
		aRows[y] = aRow.join(""); 
	}
	this.controller.get("board").innerHTML = "<tr>" + aRows.join("</tr><tr>") + "</tr>";
	//this.controller.get("board").border="1";
	
	
	//var oElements = this.controller.get("board").getElementsByTagName('td');
	StageAssistant.messageBox = this.controller.get("messages");
	//StageAssistant.board = this.controller.get("board");
	StageAssistant.board = this.controller;
	//StageAssistant.board = this;
	//StageAssistant.messageBox.update(oElements.length + " xxxx");
	if(!StageAssistant.fDebug)
	{
		StageAssistant.messageBox.style.display = 'none';
	}
	drawMaps();
	setupGame();
	//Mojo.Event.listen(this.controller.get('board'), Mojo.Event.tap,	this.selectZone.bind(this));
	//Mojo.Event.listen(this.controller.get('bingoDiv'), Mojo.Event.tap,	this.referralBingo.bind(this));
};

function StageAssistant.prototype.selectZone(this)
{
	writeMessage("tap");
}

function setupGame()
{
	//establish adjacent zones
	//done in draw borders
	
	//create players (amounts, randomly assign zones, put values on zones)
	createPlayers();
	
	//set the zones
	//var zone1 = 
	//randomly populate initial values, based on sum given to each zone
}

function createPlayers()
{
	var nPlayers = 4;
	var nInitialDice= StageAssistant.aZones.length;
	var aZones = StageAssistant.aZones.sort(function(x){return Math.random() > .5});
	var nZone = 0;
	var nZonesPerPlayer = Math.ceil(aZones.length/nPlayers);
	for(var nPlayer = 0; nPlayer < nPlayers; nPlayer++)
	{
		var oPlayer = new Player();
		oPlayer.color = StageAssistant.aColors[nPlayer % StageAssistant.aColors.length];
		//assign zones to each player
		for(var x=0; x < nZonesPerPlayer && nZone < aZones.length; x++, nZone++  )
		{
			oPlayer.zones.push(aZones[nZone]);
			aZones[nZone].setColor(oPlayer.color);
		}
		
		//add dice to the player's zones
		oPlayer.distributeDiceToZones(nInitialDice);
		StageAssistant.Players[nPlayer] = oPlayer;
	}
}


function drawMaps()
{
	//determine how many to fill in
	var fxStager = false;
	var nColor = 0;
	var nRecursion = 7;
	var nZone = 0;
	var aZones = [];
	//leave 4 blanks between each on a row
	//draw zones on every other row
	//TODO: handle same color for one zone
	for(var y=1; y < StageAssistant.nMAXHeight; y=y+2)
	{
		fxStager = !fxStager;	//rotate the start position
		//add a new block to each zone
		for(var x= (fxStager?1:3); x < StageAssistant.nMAXWidth; x=x+4 )
		{
			StageAssistant.aaoMap[y][x].zone = nZone;
			//oMessageWindow.update(StageAssistant.aaoMap[y][x].id);
			//StageAssistant.board.get(StageAssistant.aaoMap[y][x].id).innerHTML = nZone;
			StageAssistant.aZones[nZone] = new Zone();
			StageAssistant.aZones[nZone].x = x;
			StageAssistant.aZones[nZone].y = y;
			StageAssistant.aZones[nZone].zone = nZone;
			occupyCell(StageAssistant.aaoMap[y][x], nZone);
			//StageAssistant.aZones[nZone].adjacentZones.length;
			//StageAssistant.aZones[nZone].setColor()
			aZones[nZone] = {x:x, y:y, zone:nZone};
			nZone++; //TODO
		}
	}
	nZone--;	//last one was never used
	//scan left
	var aSortedZones = aZones.sort(function(elem){return Math.random()>.5});

	//fill in some vertical and horizontal lines
	for(var n=0; n < aSortedZones.length; n++)
	{
		if(Math.random() > .5) //scan left
		{
			var newX = aSortedZones[n].x-1;
			var newY = aSortedZones[n].y;
			while(validCell(newX, newY) && !StageAssistant.aaoMap[newY][newX].isOccupied)
			{
				occupyCell(StageAssistant.aaoMap[newY][newX], aSortedZones[n].zone);
				newX--;
			}
			
		}
		else//scan up
		{
			var newX = aSortedZones[n].x;
			var newY = aSortedZones[n].y-1;
			while(validCell(newX, newY) && !StageAssistant.aaoMap[newY][newX].isOccupied)
			{
				occupyCell(StageAssistant.aaoMap[newY][newX], aSortedZones[n].zone);
				newY--;
			}
		}
	}
	//fill in some vertical and horizontal lines
	aSortedZones = aZones.sort(function(elem){return Math.random()>.5});
	for(var n=0; n < aSortedZones.length; n++)
	{
		var tempZone =  aSortedZones[n].zone;
		var newX = aSortedZones[n].x;
		var newY = aSortedZones[n].y;
		while(validCell(newX, newY) && StageAssistant.aaoMap[newY][newX].zone == tempZone)
		{
			newX--;
			while( validCell(newX, newY) && !StageAssistant.aaoMap[newY][newX].isOccupied)
			{
				occupyCell(StageAssistant.aaoMap[newY][newX], tempZone);
				newY--;
			}
		}
	}

	//fill in some vertical and horizontal lines
	aSortedZones = aZones.sort(function(elem){return Math.random()>.5});
	for(var n=0; n < aSortedZones.length; n++)
	{
		var tempZone =  aSortedZones[n].zone;
		var newX = aSortedZones[n].x;
		var newY = aSortedZones[n].y;
		while(validCell(newX, newY) && StageAssistant.aaoMap[newY][newX].zone == tempZone)
		{
			newY--;
			while( validCell(newX, newY) && !StageAssistant.aaoMap[newY][newX].isOccupied)
			{
				occupyCell(StageAssistant.aaoMap[newY][newX], tempZone);
				newX--;
			}
		}
	}

	//fill in some vertical and horizontal lines	
	aSortedZones = aZones.sort(function(elem){return Math.random()>.5});
	for(var n=0; n < aSortedZones.length; n++)
	{
		var tempZone =  aSortedZones[n].zone;
		var newX = aSortedZones[n].x;
		var newY = aSortedZones[n].y;
		while(validCell(newX, newY) && StageAssistant.aaoMap[newY][newX].zone == tempZone)
		{
			newX++;
			while( validCell(newX, newY) && !StageAssistant.aaoMap[newY][newX].isOccupied)
			{
				occupyCell(StageAssistant.aaoMap[newY][newX], tempZone);
				newY++;
			}
		}
	}

	//fill in some vertical and horizontal lines
	aSortedZones = aZones.sort(function(elem){return Math.random()>.5});
	for(var n=0; n < aSortedZones.length; n++)
	{
		var tempZone =  aSortedZones[n].zone;
		var newX = aSortedZones[n].x;
		var newY = aSortedZones[n].y;
		while(validCell(newX, newY) && StageAssistant.aaoMap[newY][newX].zone == tempZone)
		{
			newY++;
			while( validCell(newX, newY) && !StageAssistant.aaoMap[newY][newX].isOccupied)
			{
				occupyCell(StageAssistant.aaoMap[newY][newX], tempZone);
				newX++;
			}
		}
	}	
	drawBorders();
}

function drawBorders()
{
	//left to right for each row
	var nMaxWidth = StageAssistant.nMAXWidth;
	for(var y=0; y < StageAssistant.nMAXHeight; y++)
	{
		var tmpZone = -1;
		for(var x=0; x < nMaxWidth ; x++)
		{
			if(StageAssistant.aaoMap[y][x].zone!=tmpZone)
			{
				//the cell is unowned, so own it, then draw the border
				while(validCell(x,y) && StageAssistant.aaoMap[y][x].zone == -1 && tmpZone!=-1)
				{
					occupyCell(StageAssistant.aaoMap[y][x], tmpZone);
					x++;
				}
				if( x < nMaxWidth)
				{
					//draw the border
					StageAssistant.board.get(StageAssistant.aaoMap[y][x].id).style.borderLeft=StageAssistant.borderStyle;
					//tell zone its neighbor, and vice versa (redundant?)
					if(StageAssistant.aaoMap[y][x].zone != -1 && tmpZone !=-1)
					{
						//StageAssistant.aZones[StageAssistant.aaoMap[y][x].zone].adjacentZones;
						StageAssistant.aZones[StageAssistant.aaoMap[y][x].zone].adjacentZones[tmpZone] = true;
						StageAssistant.aZones[tmpZone].adjacentZones[StageAssistant.aaoMap[y][x].zone] = true;
						//writeMessage(tmpZone)
					}
					tmpZone=StageAssistant.aaoMap[y][x].zone;
				}
			}
		}
	}
	//right to left of each row
	for(var y=0; y < StageAssistant.nMAXHeight; y++)
	{
		var tmpZone = -1;
		for(var x=nMaxWidth-1; x >=0 ; x--)
		{
			if(StageAssistant.aaoMap[y][x].zone!=tmpZone)
			{
				StageAssistant.board.get(StageAssistant.aaoMap[y][x].id).style.borderRight=StageAssistant.borderStyle;
				if(StageAssistant.aaoMap[y][x].zone != -1 && tmpZone !=-1)
				{
					//StageAssistant.aZones[StageAssistant.aaoMap[y][x].zone].adjacentZones;
					StageAssistant.aZones[StageAssistant.aaoMap[y][x].zone].adjacentZones[tmpZone] = true;
					StageAssistant.aZones[tmpZone].adjacentZones[StageAssistant.aaoMap[y][x].zone] = true;
					//writeMessage(tmpZone)
				}
				tmpZone=StageAssistant.aaoMap[y][x].zone;
			}
		}
	}

	//south to north for each column
	for(var x=StageAssistant.nMAXWidth-1; x >=0 ; x--)
	{
		var tmpZone = -1;
		for(var y=0; y < StageAssistant.nMAXHeight; y++)
		{
			if(StageAssistant.aaoMap[y][x].zone!=tmpZone)
			{
				StageAssistant.board.get(StageAssistant.aaoMap[y][x].id).style.borderTop=StageAssistant.borderStyle;
				if(StageAssistant.aaoMap[y][x].zone != -1 && tmpZone !=-1)
				{
					//StageAssistant.aZones[StageAssistant.aaoMap[y][x].zone].adjacentZones;
					StageAssistant.aZones[StageAssistant.aaoMap[y][x].zone].adjacentZones[tmpZone] = true;
					StageAssistant.aZones[tmpZone].adjacentZones[StageAssistant.aaoMap[y][x].zone] = true;
					//writeMessage(tmpZone)
				}
				tmpZone=StageAssistant.aaoMap[y][x].zone;
			}
		}
	}

	//north to south for each column
	for(var x=StageAssistant.nMAXWidth-1; x >=0 ; x--)
	{
		var tmpZone = -1;
		for(var y=StageAssistant.nMAXHeight-1; y >=0 ; y--)
		{
			if(StageAssistant.aaoMap[y][x].zone!=tmpZone)
			{
				StageAssistant.board.get(StageAssistant.aaoMap[y][x].id).style.borderBottom=StageAssistant.borderStyle;
				if(StageAssistant.aaoMap[y][x].zone != -1 && tmpZone !=-1)
				{
					//StageAssistant.aZones[StageAssistant.aaoMap[y][x].zone].adjacentZones;
					StageAssistant.aZones[StageAssistant.aaoMap[y][x].zone].adjacentZones[tmpZone] = true;
					StageAssistant.aZones[tmpZone].adjacentZones[StageAssistant.aaoMap[y][x].zone] = true;
					//writeMessage(tmpZone)
				}
				tmpZone=StageAssistant.aaoMap[y][x].zone;
			}
		}
	}	
}


function validCell(x,y)
{
	return !(x<0  || y < 0 || x>=StageAssistant.nMAXWidth || y>=StageAssistant.nMAXHeight)
}

function occupyCell(oCell, nZone)
{
	if(!oCell.isOccupied)
	{
		oCell.zone = nZone;
		var oDom = StageAssistant.board.get(oCell.id);
		var sOwner = StageAssistant.aColors[nZone%StageAssistant.aColors.length];
		oDom.style.backgroundColor = sOwner;
		//oCell.owner = sOwner;
		StageAssistant.aZones[nZone].cells.push(oCell)
		if(StageAssistant.fDebug)
		{
			//oDom.innerHTML = nZone;	
		}
		oCell.isOccupied = true;
	}
}


function writeMessage(sMessage)
{
	StageAssistant.messageBox.innerHTML += (sMessage) + " - ";
}










