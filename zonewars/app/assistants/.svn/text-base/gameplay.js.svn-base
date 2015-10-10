function Player() {
	/* this is the creator function for your stage assistant object */
	this.color = null;
	this.zones = [];
	this.distributeDiceToZones = function(nDice)
	{
		//um, game for this player is over!
		if(this.zones.length==0) return;
		
		var x=0;
		var nTotal = 0;
		var nZoneSize=  this.zones.length;
		var nMaxPossibleDice = nZoneSize * Zone.maxDice;
		//writeMessage(nDice +"> 0 &&"+ nTotal +"<="+ nMaxPossibleDice);
		while(nDice > 0 && nTotal <= nMaxPossibleDice )
		{
			
			var oZone = this.zones[x % nZoneSize];
			//add initial amounts for first pass
			if( x < nZoneSize )
			{
				nTotal += oZone.dice;
			}
			//randomly distribute if zone gets a die or not
			if(Math.random() > .5 && oZone.dice < Zone.maxDice)
			{
				oZone.dice = oZone.dice+1;
				nDice--;
				nTotal++;
			}
			x++;
		}
		//write the num in the cell
		for(var x=0; x < nZoneSize; x++)
		{
			this.zones[x].setCount();
		}
	}
}



function Zone() {
	/* this is the creator function for your stage assistant object */
	this.adjacentZones = {};
	this.currentColor = null;
	this.player = null;
	this.zone = -1;
	this.x=-1;
	this.y=-1;
	this.cells = [];
	this.id = '2342322234';
	this.isSelected = false;
	this.isWhite = false;
	this.setOwner = function(oPlayer)
	{
		this.player = oPlayer;
		this.setColor();
	}
	this.setColor = function (sColor)
	{
		if(!sColor)
		{
			sColor = this.player.color;
		}
		this.id = this.x+ "_"+this.y;
		for(var x=this.cells.length-1; x>=0; x-- )
		{
			var oCell = this.cells[x];
			var oDom = MainAssistant.board.get(oCell.id);
			oDom.style.backgroundColor = sColor;
			//oDom.style.backgroundColor = "pink";
		}
	}
	this.setContent = function(sContent)
	{
		var oDom = MainAssistant.board.get(this.id);
		//TODO: oDom.innerHTML = this.dice + " " + this.x + "," + this.y + "(" + this.zone + ")";
		//oDom.innerHTML +=  this.x + "," + this.y + "(" + this.zone + ")";
		oDom.innerHTML =  sContent;
		
	}
	this.setCount = function(nCount)
	{
		if(nCount)
		{
			this.dice = nCount;	
		}
		else if(nCount==0)
		{
			this.dice=1;
		}
		//this.setContent(this.zone + " Dice: " + this.dice);
		this.setContent(" Dice: " + this.dice);
	}
	this.listAdjacentZones = function()
	{
		var adjZones = [];
		for( u in this.adjacentZones)
		{
			adjZones.push(u);
		}
		return adjZones;
	}
	this.isAdjacent = function(oZone)
	{
		var aZones = this.listAdjacentZones();
		var aLabels = [];
		for(var x=0; x < aZones.length; x++)
		{
			if(aZones[x] == oZone.zone) return true;
			//aLabels.push(aZones[x].id);
//			aLabels.push((aZones[x]));
		}
//		var oDom = MainAssistant.board.get(this.id);
//		oDom.innerHTML =  oZone.zone + " <> " + aLabels.join(",");
		return false
	}
	this.selected = function()
	{
		this.isSelected = true;
		setTimeout("Zone.flashZone("+this.zone+")", 500);
	}
	this.unselected = function()
	{
		this.isSelected = false;
	}
	this.attack = function(oDefenderZone)
	{
		if(this.dice < 1) return; //sorry, more than 1 needed to do anything
		var offense = getRandomSum(this.dice);
		var defense = getRandomSum(oDefenderZone.dice)
		if(offense > defense)
		{
			//win
			oDefenderZone.setOwner(this.player);
			oDefenderZone.setCount(this.dice-1);
			writeMessage("Attacker wins: " + offense + "> " + defense);
		}
		else
		{
			//TODO: lose
			writeMessage("Attacker loses: " + offense + "<= " + defense);
		}
		
		this.setCount(1);  //no matter what, current zone will remain with 1.
	}
	this.dice = 0;
}
Zone.maxDice = 8;

Zone.flashZone = function (nZone)
{
	var oZone = MainAssistant.aZones[nZone];
	if(oZone.isSelected)
	{
		oZone.setColor((!oZone.isWhite ? "white" : oZone.player.color))
		oZone.isWhite = !oZone.isWhite;
		setTimeout("Zone.flashZone("+oZone.zone+")", 500);
	}
	else
	{
		oZone.setColor();
		oZone.isWhite = false;
	}
}


Zone.getZoneForClick = function(event)
{
	try
	{
		var aLoc = event.down.target.id.split("_");
		var nZone = MainAssistant.aaoMap[aLoc[1]][aLoc[0]].zone;
		//writeMessage(event.down.target.id+ " +");
		return MainAssistant.aZones[nZone];
	}
	catch(e)
	{
		//Mojo.log("cannot locate zone for loc " +  event.down.target.id);
		Mojo.log("cannot locate zone for loc ");
		//writeMessage("ERROR: " +  event.down.target.id)
		//cr;
	}
}


function getRandomSum(nDice)
{
	var nSum = 0;
	for(var x=0; x < nDice; x++)
	{
		nSum += (Math.floor(Math.random()*10000) %6) + 1;
	}
	return nSum;
}
