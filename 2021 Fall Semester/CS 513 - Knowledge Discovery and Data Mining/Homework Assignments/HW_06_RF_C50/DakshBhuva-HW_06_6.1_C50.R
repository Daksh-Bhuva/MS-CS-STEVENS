####################################################################

#Project    : HW_06_6.1_C50
#Purpose    : C5.0 methodology on breast-cancer-wisconsin dataset
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

##Installing package to implement C5.0 methodology 
  #install.packages("C50", repos="http://R-Forge.R-project.org")
  #install.packages("C50")
  library('C50')

## Implementing C5.0 methodology
  # ?C5.0
  C50_class <- C5.0( Class~.,data=training )
  C50_predict<-predict( C50_class ,test, type="class" )

# Plot the tree
plot(C50_class)

# Frequency table for predictions
table(actual=test[,10],C50=C50_predict)

## Calculating the error rate
  # Number of wrong predictions
  wrong<- (test[,10]!=C50_predict)
  # Error Rate in prediction of Naïve Bayes
  error_rate<-sum(wrong)/length(test[,10])

print(paste("Error Rate:" , error_rate))