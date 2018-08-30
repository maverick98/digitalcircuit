
/*
 * Your application specific code will go here
 */

Node : function(x,y,op){
          this.x = x;
		  this.y=y;
		  this.op=op;
};


function showNode(){
	Node node = new Node(12,12,'a');
	alert(node);
}
