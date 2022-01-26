####################################################################

#Project    : HW_Midterm_20211031 (#5)
#Purpose    : Naïve Bayes methodology on COVID19_v4.CSV dataset
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

## ?ifelse
  # Discretize the “MonthAtHospital” into “less than 6 months” and “6 or more months”.
  data$MonthAtHospital = ifelse(data$MonthAtHospital>=6, "6 or more months", "less than 6 months")
  # Also discretize the age into “less than 35”, “35 to 50” and “51 or over”. 
  data $Age = ifelse(data$Age<35, "less than 35", ifelse(data$Age>=51, "51 or over", "35 to 50"))

# Removing the column 'ID' as it is not required
data<-data[-1]

# View the dataset
View(data)  

# Splitting the dataset into training and test data
idx<-sort(sample(nrow(data),as.integer(.70*nrow(data))))

training<-data[idx,]
test<-data[-idx,]

# Installing package to implement Naïve Bayes methodology 
#install.packages("e1071", dependencies = TRUE)
library(e1071)

# Implementing Naïve Bayes methodology
NB <- naiveBayes(Infected ~., data = training[,-1])
predict_NB <- predict(NB, test[,-1])

# Frequency table for predictions
table(NB = predict_NB, Class = test$Infected)

## Accuracy of the model
  # Number of wrong predictions
  wrong <- sum(predict_NB != test$Infected)
  # Error Rate in prediction of Naïve Bayes
  error_rate <- wrong/length(predict_NB)

print(paste("Error Rate:" , error_rate))