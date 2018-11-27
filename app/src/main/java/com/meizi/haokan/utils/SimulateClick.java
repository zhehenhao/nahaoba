package com.meizi.haokan.utils;

import android.app.Instrumentation;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by Administrator on 2017/1/12.
 */

public class SimulateClick {

    public static void SimulateViewClick(View view, float x, float y) {
        long downTime = SystemClock.uptimeMillis();
        final MotionEvent downEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_DOWN, x, y, 0);
        downTime += 500;
        final MotionEvent upEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_UP, x, y, 0);
        view.onTouchEvent(downEvent);
        view.onTouchEvent(upEvent);
        downEvent.recycle();
        SystemClock.sleep(1000);
        upEvent.recycle();
    }
    public static void SimulateViewdoubleClick(View view, float x, float y){
        long downTime = SystemClock.uptimeMillis();
        final MotionEvent firstdownEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_DOWN, x, y, 0);
        downTime += 450;
        final MotionEvent firstupEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_UP, x, y, 0);
        downTime += 500;
        final MotionEvent seconddownEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_DOWN, x, y, 0);
        downTime += 450;
        final MotionEvent secondupEvent = MotionEvent.obtain(downTime, downTime,
                MotionEvent.ACTION_UP, x, y, 0);
        view.onTouchEvent(firstdownEvent);
        view.onTouchEvent(firstupEvent);
        view.onTouchEvent(seconddownEvent);
        view.onTouchEvent(secondupEvent);
        firstdownEvent.recycle();
        firstupEvent.recycle();
        seconddownEvent.recycle();
        secondupEvent.recycle();
    }
    public static void SimulateClick(int x ,int y){
        Instrumentation inst = new Instrumentation();
        inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),
                MotionEvent.ACTION_DOWN, x, y, 0));
        inst.sendPointerSync(MotionEvent.obtain(SystemClock.uptimeMillis(),SystemClock.uptimeMillis(),
                MotionEvent.ACTION_UP, x, y, 0));
    }
    public static void SimulateDoubleClick(int x ,int y,long  delaytime ){
        Instrumentation inst = new Instrumentation();
        long downTime = SystemClock.uptimeMillis();
        inst.sendPointerSync(MotionEvent.obtain(downTime,downTime, MotionEvent.ACTION_DOWN, x, y, 0));
        inst.sendPointerSync(MotionEvent.obtain(downTime,downTime,  MotionEvent.ACTION_UP, x, y, 0));
        SystemClock.sleep(100*delaytime);
        inst.sendPointerSync(MotionEvent.obtain(downTime,downTime,  MotionEvent.ACTION_DOWN, x, y, 0));
        inst.sendPointerSync(MotionEvent.obtain(downTime,downTime,    MotionEvent.ACTION_UP, x, y, 0));}

    public static void SimulateDownMove( float x,float y, float  shift ,long time  ){
        Instrumentation inst = new Instrumentation();
        long dowTime = SystemClock.uptimeMillis();
        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime,
                MotionEvent.ACTION_DOWN, x, y+shift,0));
        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime,
                MotionEvent.ACTION_MOVE, x, y+2*shift,0));
        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime+time,
                MotionEvent.ACTION_MOVE, x, y+3*shift,0));
        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime+time*2,
                MotionEvent.ACTION_MOVE, x, y+4*shift,0));
        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime+time*3,
                MotionEvent.ACTION_MOVE, x, y+6*shift,0));
        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime+time*4,
                MotionEvent.ACTION_UP, x, y+8*shift,0));
    }

    public static void SimulateUpMove( float x,float y, float  shift ,long time  ){
        Instrumentation inst = new Instrumentation();
        long dowTime = SystemClock.uptimeMillis();
        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime,
                MotionEvent.ACTION_DOWN, x, y-shift,0));
        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime,
                MotionEvent.ACTION_MOVE, x, y-2*shift,0));
        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime+time,
                MotionEvent.ACTION_MOVE, x, y-3*shift,0));
        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime+time*2,
                MotionEvent.ACTION_MOVE, x, y-4*shift,0));
        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime+time*3,
                MotionEvent.ACTION_MOVE, x, y-6*shift,0));
        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime+time*4,
                MotionEvent.ACTION_UP, x, y-8*shift,0));
    }

    public static void SimulateLeftMove(float x ,float y ,float shift ,long time){
        Instrumentation inst = new Instrumentation();
        long dowTime = SystemClock.uptimeMillis();
        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime,
                MotionEvent.ACTION_DOWN, x, y,0));
        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime,
                MotionEvent.ACTION_MOVE, x, y,0));
        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime+time,
                MotionEvent.ACTION_MOVE, x-shift, y,0));
        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime+2*time,
                MotionEvent.ACTION_MOVE, x-2*shift, y,0));
        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime+3*time,
                MotionEvent.ACTION_MOVE, x-3*shift, y,0));
        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime+4*time,
                MotionEvent.ACTION_UP, x-5*shift, y,0));
    }
    public static void SimulateRightMove(float x ,float y ,float shift ,long time){
        Instrumentation inst = new Instrumentation();
        long dowTime = SystemClock.uptimeMillis();
        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime,
                MotionEvent.ACTION_DOWN, x, y,0));
        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime,
                MotionEvent.ACTION_MOVE, x, y,0));
        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime+time,
                MotionEvent.ACTION_MOVE, x+shift, y,0));
        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime+2*time,
                MotionEvent.ACTION_MOVE, x+2*shift, y,0));
        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime+3*time,
                MotionEvent.ACTION_MOVE, x+3*shift, y,0));
        inst.sendPointerSync(MotionEvent.obtain(dowTime,dowTime+4*time,
                MotionEvent.ACTION_UP, x+5*shift, y,0));
    }

    public static void startautoclick(){

    }
}
