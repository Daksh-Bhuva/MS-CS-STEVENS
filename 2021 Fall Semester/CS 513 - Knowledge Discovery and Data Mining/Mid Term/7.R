####################################################################

#Project    : HW_Midterm_20211031 (#7)
#Purpose    : KNN methodology on COVID19_v4.CSV dataset
#First Name : DAKSH
#Last Name  : BHUVA
#CWID       : 10475468
#Date       : 10/31/2021

####################################################################
rm(list=ls())
cat("\014")

# Select the file from File Dialogue Box
file_name <- file.choose()

# Convert '?' into NA  
data <- read.csv(file_name)

# Remove the missing values
data<- na.omit(data)

# Column 'Infected' as a factor
data$Infected <-factor(data$Infected)
is.factor(data$Infected)

# Removing the column 'ID' and 'MaritalStatus' as it is not required
keep<-c("Age", "Exposure", "Cases", "MonthAtHospital", "Infected")
data<-data[keep]

# Function to normalize the data 
mmnorm <-function(x,minx,maxx) 
{z<-((x-minx)/(maxx-minx))
return(z) 
}

# Number of columns without column 'Infected'
x <- ncol(data)-1

# Converting the dataset into normalized form
data_normalized <- data.frame(matrix(NA,nrow = nrow(data), ncol = ncol(data)))
colnames(data_normalized)<- c(colnames(data))

for(i in c(1:x))
{
  data_normalized[,i] <- mmnorm(data[,i], min(data[,i]),max(data[,i]))
}

data_normalized[,ncol(data)] <- data[,ncol(data)]


# Splitting the dataset into training and test data
idx<-sort(sample(nrow(data_normalized),as.integer(.70*nrow(data_normalized))))

training<-data_normalized[idx,]
test<-data_normalized[-idx,]

# Installing the "kknn" package
#install.packages("kknn")
library(kknn)

## Implementing the KNN model
  # k = 5 
  predict_k5 <- kknn(formula=Infected~., training, test[,-5], k=5, kernel ="rectangular")
  fit_k5 <- fitted(predict_k5)
  
## Frequency table for predictions
  # k = 5
  table(Actual=test$Infected,Fitted=fit_k5)

## Accuracy of the model
  # Number of wrong predictions
  wrong<-(test$Infected!=fit_k5)
  # Error Rate in prediction using KNN
  error_rate<-sum(wrong)/length(wrong)

print(paste("Error Rate:" , error_rate))