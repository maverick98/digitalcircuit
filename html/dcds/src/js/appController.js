
/*
 * Your application specific code will go here
 */
require(['ojs/ojcore', 'knockout','jquery','gateUtil','expression','stack', 'ojs/ojknockout'],
  function(oj, ko,$,gateUtil,expression,stack) {
     function ControllerViewModel() {
       var self = this;

      // Media queries for repsonsive layouts
      var smQuery = oj.ResponsiveUtils.getFrameworkQuery(oj.ResponsiveUtils.FRAMEWORK_QUERY_KEY.SM_ONLY);
      self.smScreen = oj.ResponsiveKnockoutUtils.createMediaQueryObservable(smQuery);

      // Header
      // Application Name used in Branding Area
      self.appName = ko.observable("Karnaugh Map");
      // User Info used in Global Navigation area
      self.userLogin = ko.observable("maverick98");

	  var c = document.getElementById("myCanvas");
	  var ctx=c.getContext("2d");
	  gateUtil.drawANDGate(ctx,150,70,50,'violet');
	  gateUtil.drawNANDGate(ctx,300,70,40,'violet');
	  expression.showNode();
	  stack.push(12);
	  alert(stack.pop());
	


      // Footer
      function footerLink(name, id, linkTarget) {
        this.name = name;
        this.linkId = id;
        this.linkTarget = linkTarget;
      }
      self.footerLinks = ko.observableArray([
        new footerLink('About Oracle', 'aboutOracle', 'http://www.oracle.com/us/corporate/index.html#menu-about'),
        new footerLink('Contact Us', 'contactUs', 'http://www.oracle.com/us/corporate/contact/index.html'),
        new footerLink('Legal Notices', 'legalNotices', 'http://www.oracle.com/us/legal/index.html'),
        new footerLink('Terms Of Use', 'termsOfUse', 'http://www.oracle.com/us/legal/terms/index.html'),
        new footerLink('Your Privacy Rights', 'yourPrivacyRights', 'http://www.oracle.com/us/legal/privacy/index.html')
      ]);
     }
    
	  $(function() {
      
		  function init() {
			// Bind your ViewModel for the content of the whole page body.
		
			ko.applyBindings(new ControllerViewModel, document.getElementById('globalBody'));
		  }

		  // If running in a hybrid (e.g. Cordova) environment, we need to wait for the deviceready 
		  // event before executing any code that might interact with Cordova APIs or plugins.
		  if ($(document.body).hasClass('oj-hybrid')) {
			document.addEventListener("deviceready", init);
		  } else {
			init();
		  }

    });

		
     
  }
 
);
