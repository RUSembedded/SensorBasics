package com.example.berner.myapplication;

/**
 * Created by berner on 18.12.2015.
 */
public abstract class Navigation {

    public static double[] updateDCM(double[] c_b_r_k1, double[] c_b_r, double[] w_b_ib, double T){

        //Zeile 1
        c_b_r_k1[0] += (c_b_r[1]*w_b_ib[2] - c_b_r[2]*w_b_ib[1])*T;
        c_b_r_k1[1] += (c_b_r[2]*w_b_ib[0] - c_b_r[0]*w_b_ib[2])*T;
        c_b_r_k1[2] += (c_b_r[0]*w_b_ib[1] - c_b_r[1]*w_b_ib[0])*T;

        //Zeile2
        c_b_r_k1[3] += (c_b_r[4]*w_b_ib[2] - c_b_r[5]*w_b_ib[1])*T;
        c_b_r_k1[4] += (c_b_r[5]*w_b_ib[0] - c_b_r[3]*w_b_ib[2])*T;
        c_b_r_k1[5] += (c_b_r[3]*w_b_ib[1] - c_b_r[4]*w_b_ib[0])*T;

        //Zeile 3
        c_b_r_k1[6] += (c_b_r[7]*w_b_ib[2] - c_b_r[8]*w_b_ib[1])*T;
        c_b_r_k1[7] += (c_b_r[8]*w_b_ib[0] - c_b_r[6]*w_b_ib[2])*T;
        c_b_r_k1[8] += (c_b_r[6]*w_b_ib[1] - c_b_r[7]*w_b_ib[0])*T;

        return c_b_r_k1;
    }

    public static double[] moveDCM(double[] c_b_r_k1){
        double[] c_b_r = new double[9];

        for (int i= 0; i<c_b_r.length; i++){
            c_b_r[i] = c_b_r_k1[i];
        }
        return  c_b_r;
    }

    public static double[] rotateVectorDCM(double[] c, double[] inVector){
        double[] outVector = new double[3];
        for (int i=0; i< c.length; i+=3){
            outVector[i/3]=0;
            for (int k=0; k<inVector.length; k++){
                outVector[i/3] += c[i+k]*inVector[k];
            }
        }
        return outVector;
    }
}
