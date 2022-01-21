####################################################################

#Project    : HW_07_ANN
#Purpose    : ANN Methodology with five (5) nodes in the hidden layer on breast-cancer-wisconsin dataset
#First Name : DAKSH
#Last Name  : BHUVA
#CWID       : 10475468
#Date       : 11/15/2021

####################################################################
rm(list=ls())
cat("\014")

# Loading the dataset and c-onvert '?' into NA 
data = read.csv("/Users/dakshbhuva/Desktop/CS-513 KDD/wisc_bc_ContinuousVar.csv", na.strings = "?")

# Factoring the dataset
data$diagnosis <-factor(data$diagnosis)

# Removing the missing values
data<-data.frame(lapply(na.omit(data),as.numeric))

# Removing the column 'Sample' as it is not required
data<-data[-1]

### Normalizing the data
df <- as.data.frame(apply(data[,1:ncol(data)],2,function(x) (x - min(x))/(max(x)-min(x))))

### Splitting the dataset into training and test data
idx<-sort(sample(nrow(df),as.integer(.70*nrow(df))))

training<-df[idx,]
test<-df[-idx,]

### Installing package to implement ANN methodology 
#install.packages("neuralnet")
library("neuralnet")
#?neuralnet()

### Implementing ANN methodology with five (5) nodes in the hidden layer
model<- neuralnet( diagnosis~., training, hidden=5, exclude = NULL, threshold=0.01)
pred<- predict(model, test)

### Discretizing the predicted values into '0' and '1'
pred_dis <- ifelse(pred<0.5,0,1)

### Plotting the model
plot(model)

### Frequency table for predictions
table(Actual = test$diagnosis, Prediction = pred_dis)

### Calculating the error rate
# Number of wrong predictions
wrong<- (test$diagnosis!=pred_dis)
# Error Rate in prediction of ANN
error_rate<-sum(wrong)/length(test$diagnosis)

print(paste("Error Rate:" , error_rate))
