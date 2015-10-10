function AppAssistant() {
	/* this is the creator function for your stage assistant object */
}
AppAssistant.prototype.handleCommand = function(event) {   
  var stageController = this.controller.getStageController("StageAssistant");   
  if(stageController && event.type == Mojo.Event.command) {   
    var currentScene = stageController.activeScene();   
    switch(event.command) {   
    case 'do-myAbout':   
      currentScene.showAlertDialog({   
      onChoose: function(value) {},   
      title: $L("My App - v1.0"),   
      message: $L("Copyright 2008-2009, My Company, Inc."),   
      choices:[{label:$L("OK"), value:""}]   
      });   
      break;   
    case 'do-myHelp':   
      stageController.pushScene("help");   
      break;   
    }   
  }   
}; 