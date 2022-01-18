####################################################################

#Project    : HW_04_NB 
#Purpose    : Naïve Bayes methodology on breast-cancer-wisconsin dataset
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

# Installing package to implement Naïve Bayes methodology 
install.packages("e1071", dependencies = TRUE)
library(e1071)

# Implementing Naïve Bayes methodology
NB <- naiveBayes(Class ~., data = training)
predict_NB <- predict(NB, test)

# Frequency table for predictions
table(NB = predict_NB, Class = test$Class)
#prop.table(table(NB = predict_NB, class=test$Class))

## Calculating the error rate
  # Number of wrong predictions
  wrong <- sum(predict_NB != test$Class)
  # Error Rate in prediction of Naïve Bayes
  error_rate <- wrong/length(predict_NB)
  
print(paste("Error Rate:" , error_rate))