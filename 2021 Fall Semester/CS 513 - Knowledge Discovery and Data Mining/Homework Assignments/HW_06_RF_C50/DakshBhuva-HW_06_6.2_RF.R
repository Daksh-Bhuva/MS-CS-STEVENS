####################################################################

#Project    : HW_06_6.2_RF
#Purpose    : Random Forest methodology on breast-cancer-wisconsin dataset
#First Name : DAKSH
#Last Name  : BHUVA
#CWID       : 10475468
#Date       : 11/15/2021

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

#?sample()
#?sort()

# Splitting the dataset into training and test data
idx<-sort(sample(nrow(data),as.integer(.70*nrow(data))))

training<-data[idx,]
test<-data[-idx,]

##Installing package to implement Random Forest methodology 
  #install.packages('randomForest')
  library(randomForest)

# Implementing Random Forest methodology
fit <- randomForest( Class~., data=training, importance=TRUE, ntree=1000)

# Identify and plot important features
importance(fit)
varImpPlot(fit)
RF_predict <- predict(fit, test)

# Frequency table for predictions
table(actual=test[,10],RF_predict)

## Calculating the error rate
  # Number of wrong predictions
  wrong<- (test[,10]!=RF_predict)
  # Error Rate in prediction of Naïve Bayes
  error_rate<-sum(wrong)/length(test[,10])

print(paste("Error Rate:" , error_rate))