package com.example.chartpie;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
/**
 * liyakun
 * @author Administrator
 *
 */
public class PanelPieChart2 extends View{

	
	 private int ScrWidth,ScrHeight;   
     
     //外部传入的比例参数    
    private final float arrPer[] = new float[]{15f,36f,14f,35f};    
    //RGB颜色数组  
    private final int arrColorRgb[][] = { {77, 83, 97},    
                                          {148, 13, 181},    
                                          {253, 46, 90},  
                                          {52, 46, 188}} ;  
     //指定突出哪个块  
    private final int offsetBlock = 2;  
      
public PanelPieChart2(Context context) {  
        super(context);  
        // TODO Auto-generated constructor stub  
          
        //屏幕信息  
        DisplayMetrics dm = getResources().getDisplayMetrics();  
        ScrHeight = dm.heightPixels;  
        ScrWidth = dm.widthPixels;  
    }  


 
     
   public PanelPieChart2(Context context, AttributeSet attrs, int defStyleAttr) {
	super(context, attrs, defStyleAttr);
	// TODO Auto-generated constructor stub
	
	 //屏幕信息  
    DisplayMetrics dm = getResources().getDisplayMetrics();  
    ScrHeight = dm.heightPixels;  
    ScrWidth = dm.widthPixels;  
}




public PanelPieChart2(Context context, AttributeSet attrs) {
	super(context, attrs);
	// TODO Auto-generated constructor stub
	
	 //屏幕信息  
    DisplayMetrics dm = getResources().getDisplayMetrics();  
    ScrHeight = dm.heightPixels;  
    ScrWidth = dm.widthPixels;  
}




public void onDraw(Canvas canvas){  
       //画布背景  
       canvas.drawColor(Color.WHITE);                  
           
       float cirX = ScrWidth / 2;    
       float cirY = ScrHeight / 3 ;    
       float radius = ScrHeight / 5 ;//150;    
                                   
       float arcLeft = cirX - radius;    
       float arcTop  = cirY - radius ;    
       float arcRight = cirX + radius ;    
       float arcBottom = cirY + radius ;    
       RectF arcRF0 = new RectF(arcLeft ,arcTop,arcRight,arcBottom);     
         
       //画笔初始化  
       Paint PaintArc = new Paint();         
       Paint PaintLabel = new Paint();    
       PaintLabel.setColor(Color.WHITE);  
       PaintLabel.setTextSize(16);  
       
       Paint paint= new Paint();
       paint.setColor(Color.BLUE);
       paint.setAntiAlias(true);
       paint.setStyle(Style.STROKE);
       //位置计算类    
       XChartCalc xcalc = new XChartCalc();      
         
       float Percentage = 0.0f;  
       float CurrPer = 0.0f;  
       int i= 0;    
       for(i=0; i<arrPer.length; i++)     
       {    
           //将百分比转换为饼图显示角度    
           Percentage = 360 * (arrPer[i]/ 100);    
           Percentage = (float)(Math.round(Percentage *100))/100;    
           //分配颜色              
           PaintArc.setARGB(255,arrColorRgb[i][0], arrColorRgb[i][1], arrColorRgb[i][2]);  
                
             
           if( i == offsetBlock) //指定突出哪个块  
           {  
               //偏移圆心点位置  
               float newRadius = radius /10;  
                //计算百分比标签  
               xcalc.CalcArcEndPointXY(cirX, cirY, newRadius , CurrPer + Percentage/2);      
               canvas.drawCircle(cirX,cirY, newRadius,paint);
               arcLeft = xcalc.getPosX() - radius;    
               arcTop  = xcalc.getPosY() - radius ;    
               arcRight = xcalc.getPosX() + radius ;    
               arcBottom = xcalc.getPosY() + radius ;    
               RectF arcRF1 = new RectF(arcLeft ,arcTop,arcRight,arcBottom);   //确定突出部分的圆心    
               canvas.drawRect(arcRF1, paint);
             //在饼图中显示所占比例    
               canvas.drawArc(arcRF1, CurrPer, Percentage, true, PaintArc);    //圆的半径不变，圆心移动 
                 
               //计算百分比标签  
               xcalc.CalcArcEndPointXY(xcalc.getPosX(), xcalc.getPosY(), radius - radius/2/2, CurrPer + Percentage/2);       
               //标识  
               canvas.drawText(Float.toString(arrPer[i])+"%",xcalc.getPosX(), xcalc.getPosY() ,PaintLabel);      
                  
                  
            }else{  
                //在饼图中显示所占比例    
                canvas.drawArc(arcRF0, CurrPer, Percentage, true, PaintArc);                   
                //计算百分比标签  
                xcalc.CalcArcEndPointXY(cirX, cirY, radius - radius/2/2, CurrPer + Percentage/2);     
                //标识  
                canvas.drawText(Float.toString(arrPer[i])+"%",xcalc.getPosX(), xcalc.getPosY() ,PaintLabel);          
              
            }  
              
            //下次的起始角度    
            CurrPer += Percentage;    
        }    
            
    }  
  


}
