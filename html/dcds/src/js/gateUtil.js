
/*
 * Your application specific code will go here
 */
 function drawLine (ctx, x1,y1,x2,y2){
	ctx.moveTo(x1,y1);
	ctx.lineTo(x2,y2);

 }
function drawANDGate(ctx, x , y , r,color){
	drawANDGateInternal(ctx, x , y , r,color,false);
}
function drawNANDGate(ctx, x , y , r,color){
	drawANDGateInternal(ctx, x , y , r,color,true);
}

function drawANDGateInternal(ctx, x , y , r,color,isNand){
	ctx.beginPath();
	ctx.fillStyle=color;
	
   ctx.arc(x , y, r, 0.5 *Math.PI, 1.5*Math.PI);
 	drawLine(ctx,x,y-r,x+2*r/3,y-r);
 	drawLine(ctx,x,y+r,x+2*r/3,y+r);
	
	ctx.fillRect(x,y-r,2*r/3,2*r); 
    drawLine(ctx,x+2*r/3,y-r,x+2*r/3,y+r);
	
    drawLine(ctx,x+2*r/3,y-r/2,x+r,y-r/2);
	drawLine(ctx,x+2*r/3,y+r/2,x+r,y+r/2);

	drawLine(ctx,x+r,y-r/2,x+r,y-1.3*r);
    drawLine(ctx,x+r,y-1.3*r,x+3*r/2,y-1.3*r);

    drawLine(ctx,x+r,y+r/2,x+r,y+1.3*r);
	drawLine(ctx,x+r,y+1.3*r,x+3*r/2,y+1.3*r);
    ctx.fill();
    ctx.stroke();
	if(isNand){
		ctx.beginPath();
		ctx.arc(x-r-r/6 , y, r/6, 0, 2*Math.PI);
		ctx.fill();
		ctx.stroke();
	}
}
