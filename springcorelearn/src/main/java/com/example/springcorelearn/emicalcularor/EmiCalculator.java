package com.example.springcorelearn.emicalcularor;


public class EmiCalculator {
 public double emiCalculate(double p, double r, double t) {
	 r=r/(12*100);
     t=t*12; 

     return (p*r*Math.pow(1+r,t))/(Math.pow(1+r,t)-1);
	 
 }
}
