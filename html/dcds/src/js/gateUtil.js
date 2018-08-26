
/*
 * Your application specific code will go here
 */
 function drawLine (ctx, x1,y1,x2,y2){
	ctx.moveTo(x1,y1);
	ctx.lineTo(x2,y2);

 }
function drawANDGate(ctx, x , y , r,up,down,color){
	ctx.beginPath();
    ctx.arc(x+25 , y, r, 0.5 *Math.PI, 1.5*Math.PI);
	drawLine(ctx,x+25,y-r,x+25,y+r);
	drawLine(ctx,x+25,y-15,x+35,y-15);
  	drawLine(ctx,x+25,y+15,x+35,y+15);
	drawLine(ctx,x+35,y-15,x+35,y+15);
	drawLine(ctx,x+35,y-10,x+45,y-10);
 	drawLine(ctx,x+35,y+10,x+45,y+10);
	drawLine(ctx,x+45,y-10,x+45,y-25);
 	drawLine(ctx,x+45,y+10,x+45,y+25);
    drawLine(ctx,x+45,y-25,x+60,y-25);
	drawLine(ctx,x+45,y+25,x+60,y+25);
	drawLine(ctx,x+60,y-25,x+60,y-25-up);
	drawLine(ctx,x+60,y+25,x+60,y+25+down);
    ctx.fillStyle=color;
    ctx.fill();
    ctx.stroke();
}