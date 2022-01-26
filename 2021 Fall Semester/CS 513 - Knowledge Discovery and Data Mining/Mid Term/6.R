####################################################################

#Project    : HW_Midterm_20211031 (#6)
#Purpose    : CART methodology on COVID19_v4.CSV dataset
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

## Installing package to implement CART methodology
  #install.packages("rpart")
  #install.packages("rpart.plot")     # Enhanced tree plots
  #install.packages("rattle")         # Fancy tree plot
  #install.packages("RColorBrewer")   # colors needed for rattle
  library(rpart)
  library(rpart.plot)  			# Enhanced tree plots
  library(rattle)           # Fancy tree plot
  library(RColorBrewer)     # colors needed for rattle

# Splitting the dataset into training and test data
idx<-sort(sample(nrow(data),as.integer(.70*nrow(data))))

training<-data[idx,]
test<-data[-idx,]

## Implementing CART methodology
  #Grow the tree 
  #?rpart() 
  CART<-rpart( Infected~., data=training[,-1])
  # Plot the tree
  rpart.plot(CART)

CART_predict<-predict(CART, test, type="class")

df<-as.data.frame(cbind(test, CART_predict))

# Frequency table for predictions
table(Actual=test[,"Infected"], CART = CART_predict)

## Accuracy of the model
  # Number of wrong predictions
  CART_wrong<-sum(test[,"Infected"] != CART_predict)
  # Error Rate in prediction of CART
  error_rate=CART_wrong/length(test$Infected)
  
print(paste("Error Rate:" , error_rate))