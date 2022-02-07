
#########################################################
##  Purpose: Ann - LaRose Example in Matrix
##  Developer: KD         
##
#########################################################

#########################################################
##  Step 0: Clear the environment
##           
##
#########################################################
rm(list=ls())

## define the initial weights, input and sigmoid function

weight_ih<- c( 0.5, 0.6, 0.8, 0.6, 0.7 ,0.9, 0.8, 0.4)
Mw_ih<- matrix(weight_ih , nrow = 4, ncol = 2,byrow = FALSE)
weight_ho<-c(0.5,0.9,0.9)
Mw_ho<-matrix(weight_ho,nrow=3,ncol=1)

i<-c(1,	0.4,	0.2,	0.7) 

sigmoid <-function(x )
{ z=1/(1+exp(-x))
return(z)                              
}


# i %*% Mw_ih

out_hiddenL<-  sigmoid (i %*% Mw_ih) 
 
input_outputL<- c(1,out_hiddenL)
is.vector(input_outputL)

out_outL<-  sigmoid (  input_outputL%*% Mw_ho )

## Back propegation calculation
Actual<-.8
eta<- .1
e<-out_outL-Actual
delta<- -1*(out_outL)*(1-out_outL)*e
DELTA<-eta*delta%*%input_outputL

#-0.000820411
#-0.000647454
#-0.000670747
New_ho<-Mw_ho+t(DELTA)

#0.499179589
#0.899352546
#0.899329253

e_hidden<-Mw_ho[-1]%*%delta
#-0.007383703
#-0.007383703

delta_h<- (t(e_hidden)*(-1*(out_hiddenL)*(1-out_hiddenL))) 
 
#0.7891817*0.2108183 
#0.8175745*0.1824255

DELTA<-eta*(as.matrix(i)%*%delta_h)




