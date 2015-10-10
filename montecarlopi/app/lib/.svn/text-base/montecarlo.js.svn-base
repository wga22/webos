function MonteCarlo() {
	/* this is the creator function for your stage assistant object */
}


function convertAccelValues(nMaxVal,nLength,nPosAndNeg)
{
	nLength = nLength==0 ? Math.random() : nLength;
	//var tens = Math.round((Math.random() * 1000000)/100000)+1;
	var fPosOrNeg = Math.random() > 0.5;
	
	//shift to a new spot in the results
	var nVal = Math.round((nLength  * nDigit * Math.random() ))  % nMaxVal;
	Mojo.Log.info("tens: " + nDigit + " - val " + nVal);
	return fPosOrNeg ? -nVal : nVal; 
}


MonteCarlo.randomSpot = function (nMaxVal)
{
	var nVal = (Math.random() * 10000) % nMaxVal;
	return (Math.random() >= .5) ? -nVal : nVal; 
}



MonteCarlo.getRowsOfPiDetails = function (nCnt, nCntInside)
{
	var rawScore = nCntInside/nCnt;
	var percentHit = (Math.round(rawScore*1000000))/10000;
	var pi = 4 * rawScore;	

	var aLines = [("Total Dots(T): " + nCnt),
	              ("Hits(H): " + nCntInside),
	              ("Percent inside: " + percentHit + "%"), 
	              ("PI approximation: " + pi),
	              ("Percent different from PI: " + (Math.round(Math.abs((Math.PI-pi)/Math.PI)*1000000)/10000) + "%")
	              ];
	return aLines;
}


MonteCarlo.randomSpotOLD = function (nMaxVal)
{
	//to compensate for possible weak values in certain ranges of random, randomize the digits used
	var nDigit = Math.pow(10, Math.ceil(Math.random()*8));
	var nVal = (Math.random() * nDigit) % nMaxVal;
	return (Math.random() >= .5) ? -nVal : nVal; 
}
