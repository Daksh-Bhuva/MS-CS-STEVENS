####################################################################

#Project    : HW_03_KNN 
#Purpose    : KNN Methodology on breast-cancer-wisconsin dataset
#First Name : DAKSH
#Last Name  : BHUVA
#CWID       : 10475468
#Date       : 10/18/2021

####################################################################
rm(list=ls())
cat("\014")

# Select the file from File Dialogue Box
file_name <- file.choose()
# Convert '?' into NA  
data <- read.csv(file_name, na.strings = '?')

# Remove the missing values
data<- na.omit(data)

# Removing the column 'Sample' as it is not required
data<-data[-1]

# Column 'Class' as a factor of 'benign' and 'malignant'
data$Class <-factor(data$Class,levels = c(2,4), labels = c("benign","malignant"))

# View the dataset
View(data)

# Function to normalize the data 
mmnorm <-function(x,minx,maxx) 
{z<-((x-minx)/(maxx-minx))
 return(z) 
}

# Number of columns without column 'Class'
x <- ncol(data)-1

# Converting the dataset into normalized form
data_normalized <- data.frame(matrix(NA,nrow = nrow(data), ncol = ncol(data)))
colnames(data_normalized)<- c(colnames(data))

for(i in c(1:x))
{
  data_normalized[,i] <- mmnorm(data[,i], min(data[,i]),max(data[,i]))
}

data_normalized[,ncol(data)] <- data[,ncol(data)]


#?sample()
#?sort()

# Splitting the dataset into training and test data
idx<-sort(sample(nrow(data_normalized),as.integer(.70*nrow(data_normalized))))

training<-data_normalized[idx,]
test<-data_normalized[-idx,]


?install.packages
installed.packages()

# Installing the "kknn" package
install.packages("kknn")
library(kknn)

#?kknn()

## Implementing the KNN model
  # k = 3 
  predict_k3 <- kknn(formula=Class~., training, test[,-11], k=3,kernel ="rectangular")
  fit_k3 <- fitted(predict_k3)

  # k = 5 
  predict_k5 <- kknn(formula=Class~., training, test[,-11], k=5,kernel ="rectangular")
  fit_k5 <- fitted(predict_k5)

  # k = 10 
  predict_k10 <- kknn(formula=Class~., training, test[,-11], k=10,kernel ="rectangular")
  fit_k10 <- fitted(predict_k10)

## Frequency table for predictions
  # k = 3 
  table(Actual=test$Class,Fitted=fit_k3)
  
  # k = 5 
  table(Actual=test$Class,Fitted=fit_k5)
  
  # k = 10
  table(Actual=test$Class,Fitted=fit_k10)