# -*- coding: utf-8 -*-
"""Assignment_1_Q5.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/1U_5WhmE6cbQTZSQp9BzeA3hZpm3fiDs0

# HW1: Logistic Regression.

### Name: [Daksh Bhuva]

#### For this assignment, you will build 6 models. You need to train Logistic Regression/Regularized Logistic Regression each with Batch Gradient Descent, Stochastic Gradient Descent and Mini Batch Gradient Descent. Also you should plot their objective values versus epochs and compare their training and testing accuracies. You will need to tune the parameters a little bit to obtain reasonable results.

#### You do not have to follow the following procedure. You may implement your own functions and methods, but you need to show your results and plots.
"""

# Load Packages

import pandas as pd
import numpy as np

"""# 1. Data processing

- Download the Breast Cancer dataset from canvas or from https://archive.ics.uci.edu/ml/datasets/breast+cancer+wisconsin+(diagnostic)
- Load the data.
- Preprocess the data.

## 1.1. Load the data
"""

dataset = pd.read_csv('/content/data.csv')
dataset.head()

"""## 1.2 Examine and clean data"""

# Some columns may not be useful for the model (For example, the first column contains ID number which may be irrelavant). 
# You need to get rid of the ID number feature.
# Also you should transform target labels in the second column from 'B' and 'M' to 1 and -1.

dataset.drop(dataset.columns[len(dataset.columns)-1], axis=1, inplace=True)
dataset.drop(dataset.columns[0], axis=1, inplace=True)
dataset.diagnosis = dataset.diagnosis.map({"B": 1, "M": -1})
dataset.head()

"""## 1.3. Partition to training and testing sets"""

# y is the Target attribute 'diagnosis' and X is rest of the attributes

dvalues = dataset.values
X, y = dvalues[:, 1:], dvalues[:, 0:1]

# You can partition using 80% training data and 20% testing data. It is a commonly used ratio in machinel learning.

from sklearn.model_selection import train_test_split
x_train, x_test, y_train, y_test = train_test_split(X, y, test_size = 0.2, random_state=0)

"""## 1.4. Feature scaling

Use the standardization to trainsform both training and test features
"""

# Standardization
import numpy

# calculate mu and sig using the training set
d = x_train.shape[1]
mu = numpy.mean(x_train, axis=0).reshape(1, d)
sig = numpy.std(x_train, axis=0).reshape(1, d)

# transform the training features
x_train = (x_train - mu) / (sig + 1E-6)

# transform the test features
x_test = (x_test - mu) / (sig + 1E-6)

print('test mean = ')
print(numpy.mean(x_test, axis=0))

print('test std = ')
print(numpy.std(x_test, axis=0))

"""# 2.  Logistic Regression Model

The objective function is $Q (w; X, y) = \frac{1}{n} \sum_{i=1}^n \log \Big( 1 + \exp \big( - y_i x_i^T w \big) \Big) + \frac{\lambda}{2} \| w \|_2^2 $.

When $\lambda = 0$, the model is a regular logistric regression and when $\lambda > 0$, it essentially becomes a regularized logistric regression.
"""

# Calculate the objective function value, or loss
# Inputs:
#     w: weight: d-by-1 matrix
#     x: data: n-by-d matrix
#     y: label: n-by-1 matrix
#     lam: regularization parameter: scalar
# Return:
#     objective function value, or loss (scalar)

def objective(w, x, y, lam):
    expTerm = np.exp((-(np.dot(np.multiply(y, x), w))))
    logTerm = np.log(1 + expTerm)

    return np.mean(logTerm) + (lam/2 * np.sum(w*w))
    pass

"""# 3. Numerical optimization

## 3.1. Gradient descent

The gradient at $w$ for regularized logistic regression is  $g = - \frac{1}{n} \sum_{i=1}^n \frac{y_i x_i }{1 + \exp ( y_i x_i^T w)} + \lambda w$
"""

# Calculate the gradient
# Inputs:
#     w: weight: d-by-1 matrix
#     x: data: n-by-d matrix
#     y: label: n-by-1 matrix
#     lam: regularization parameter: scalar
# Return:
#     g: gradient: d-by-1 matrix

def gradient(w, x, y, lam):
    n, d = x.shape
    numerator = np.multiply(y, x)
    denominator = 1 + np.exp(((np.dot(np.multiply(y, x), w))))
    meanTerm = np.divide(numerator, denominator)

    return -np.mean(meanTerm, axis=0).reshape(d, 1) + lam*w 
    pass

# Gradient descent for solving logistic regression
# You will need to do iterative process (loops) to obtain optimal weights in this function

# Inputs:
#     x: data: n-by-d matrix
#     y: label: n-by-1 matrix
#     lam: scalar, the regularization parameter
#     learning_rate: scalar
#     w: weights: d-by-1 matrix, initialization of w
#     max_epoch: integer, the maximal epochs
# Return:
#     w: weights: d-by-1 matrix, the solution
#     objvals: a record of each epoch's objective value

def gradient_descent(x, y, lam, learning_rate, w, max_epoch=100):
    n, d = x.shape
    objvals = numpy.zeros(max_epoch)
    
    for i in range(max_epoch):
        objval = objective(w, x, y, lam)
        objvals[i] = objval
        print('Objective value at ' + str(i+1) + ' is ' + str(objval))
        gradientTerm = gradient(w, x, y, lam)
        w = w - learning_rate * gradientTerm
    
    return w, objvals
    pass

"""Use gradient_descent function to obtain your optimal weights and a list of objective values over each epoch."""

# Train logistic regression
# You should get the optimal weights and a list of objective values by using gradient_descent function.

lam = 0
learning_rate = 1
w = np.zeros((d, 1))

w_gd, objvals_gd = gradient_descent(x_train, y_train, lam, learning_rate, w)

# Train regularized logistic regression
# You should get the optimal weights and a list of objective values by using gradient_descent function.

lam = 0.0001
learning_rate = 1
w = np.zeros((d, 1))

w_gd_regularized, objvals_gd_regularized = gradient_descent(x_train, y_train, lam, learning_rate, w)

"""## 3.2. Stochastic gradient descent (SGD)

Define new objective function $Q_i (w) = \log \Big( 1 + \exp \big( - y_i x_i^T w \big) \Big) + \frac{\lambda}{2} \| w \|_2^2 $. 

The stochastic gradient at $w$ is $g_i = \frac{\partial Q_i }{ \partial w} = -\frac{y_i x_i }{1 + \exp ( y_i x_i^T w)} + \lambda w$.

You may need to implement a new function to calculate the new objective function and gradients.
"""

# Calculate the objective Q_i and the gradient of Q_i
# Inputs:
#     w: weights: d-by-1 matrix
#     xi: data: 1-by-d matrix
#     yi: label: scalar
#     lam: scalar, the regularization parameter
# Return:
#     obj: scalar, the objective Q_i
#     g: d-by-1 matrix, gradient of Q_i

def stochastic_objective_gradient(w, xi, yi, lam):
    d = xi.shape[0]
    expTerm = float(np.dot(yi * xi, w))
    logTerm = np.log(1 + np.exp(-expTerm)) 
    obj = logTerm + lam/2 * np.sum(w*w) 
    
    gTerm = -(yi * xi).T / (1 + np.exp(expTerm)) 
    g = gTerm + lam * w
    
    return obj, g
    pass

"""Hints:
1. In every epoch, randomly permute the $n$ samples.
2. Each epoch has $n$ iterations. In every iteration, use 1 sample, and compute the gradient and objective using the ``stochastic_objective_gradient`` function. In the next iteration, use the next sample, and so on.
"""

# SGD for solving logistic regression
# You will need to do iterative process (loops) to obtain optimal weights in this function

# Inputs:
#     x: data: n-by-d matrix
#     y: label: n-by-1 matrix
#     lam: scalar, the regularization parameter
#     learning_rate: scalar
#     w: weights: d-by-1 matrix, initialization of w
#     max_epoch: integer, the maximal epochs
# Return:
#     
#     w: weights: d-by-1 matrix, the solution
#     objvals: a record of each epoch's objective value
#     Record one objective value per epoch (not per iteration)

def sgd(x, y, lam, learning_rate, w, max_epoch=100):
    n, d = x.shape
    objvals = np.zeros(max_epoch) 
    
    for i in range(max_epoch):
        
        rand_indices = numpy.random.permutation(n)
        x_rand = x[rand_indices, :]
        y_rand = y[rand_indices, :]
        objval = 0 
        for j in range(n):
            xi = x_rand[j, :].reshape((1,d))
            yi = float(y_rand[j, :]) 
            obj, g = stochastic_objective_gradient(w, xi, yi, lam)
            objval = objval + obj
            w = w - learning_rate * g
        
        learning_rate = learning_rate * 0.9
        objval = objval/n
        objvals[i] = objval
        print('Objective value at epoch ' + str(i+1) + ' is ' + str(objval))
    
    return w, objvals
    pass

"""Use sgd function to obtain your optimal weights and a list of objective values over each epoch."""

# Train logistic regression
# You should get the optimal weights and a list of objective values by using gradient_descent function.

lam = 0
learning_rate = 0.1
w = numpy.zeros((d,1))

w_sgd, objvals_sgd = sgd(x_train, y_train, lam, learning_rate, w)

# Train regularized logistric regression
# You should get the optimal weights and a list of objective values by using gradient_descent function.

lam = 0.0001
learning_rate = 0.1
w = numpy.zeros((d,1))

w_sgd_regularized, objvals_sgd_regularized = sgd(x_train, y_train, lam, learning_rate, w)

"""## 3.3 Mini-Batch Gradient Descent (MBGD)

Define $Q_I (w) = \frac{1}{b} \sum_{i \in I} \log \Big( 1 + \exp \big( - y_i x_i^T w \big) \Big) + \frac{\lambda}{2} \| w \|_2^2 $, where $I$ is a set containing $b$ indices randomly drawn from $\{ 1, \cdots , n \}$ without replacement.

The stochastic gradient at $w$ is $g_I = \frac{\partial Q_I }{ \partial w} = \frac{1}{b} \sum_{i \in I} \frac{- y_i x_i }{1 + \exp ( y_i x_i^T w)} + \lambda w$.

You may need to implement a new function to calculate the new objective function and gradients.
"""

# Calculate the objective Q_I and the gradient of Q_I
# Inputs:
#     w: weights: d-by-1 matrix
#     xi: data: b-by-d matrix
#     yi: label:  b-by-1 matrix
#     lam: scalar, the regularization parameter
# Return:
#     obj: scalar, the objective Q_i
#     g: d-by-1 matrix, gradient of Q_i

def mb_objective_gradient(w, xi, yi, lam):
    n,d = xi.shape
    expTerm = np.dot(yi * xi, w) 
    
    logTerm = np.log(1 + np.exp(-expTerm)) 
    obj = np.mean(logTerm) + lam/2 * np.sum(w*w) 

    gTerm = - np.mean(np.divide(yi * xi, 1 + np.exp(expTerm)), axis = 0).reshape(d,1) 
    g = gTerm + lam * w 
    
    return obj, g
    pass

"""Hints:
1. In every epoch, randomly permute the $n$ samples (just like SGD).
2. Each epoch has $\frac{n}{b}$ iterations. In every iteration, use $b$ samples, and compute the gradient and objective using the ``mb_objective_gradient`` function. In the next iteration, use the next $b$ samples, and so on.
"""

# MBGD for solving logistic regression
# You will need to do iterative process (loops) to obtain optimal weights in this function

# Inputs:
#     x: data: n-by-d matrix
#     y: label: n-by-1 matrix
#     lam: scalar, the regularization parameter
#     learning_rate: scalar
#     w: weights: d-by-1 matrix, initialization of w
#     max_epoch: integer, the maximal epochs
# Return:
#     w: weights: d-by-1 matrix, the solution
#     objvals: a record of each epoch's objective value
#     Record one objective value per epoch (not per iteration)

def mbgd(x, y, lam, learning_rate, w, max_epoch=100):
    n, d = x.shape
    batch_size = 10
    objvals = numpy.zeros(max_epoch)
    
    for i in range(max_epoch):
        rand_indices = numpy.random.permutation(n)
        x_rand = x[rand_indices, :]
        y_rand = y[rand_indices, :]
        
        objval = 0 
        for j in range(0, n, batch_size):
            xi = x_rand[j:j+batch_size, :] 
            yi = y_rand[j:j+batch_size, :] 
            obj, g = mb_objective_gradient(w, xi, yi, lam)
            objval = objval + obj
            w = w - learning_rate * g
        
        learning_rate = learning_rate * 0.9 
        objval /= n/batch_size
        objvals[i] = objval
        print('Objective value at epoch ' + str(i+1) + ' is ' + str(objval))
    
    return w, objvals
    pass

"""Use mbgd function to obtain your optimal weights and a list of objective values over each epoch."""

# Train logistic regression
# You should get the optimal weights and a list of objective values by using gradient_descent function.

lam = 0
learning_rate = 1
w = numpy.zeros((d, 1))

w_mbgd, objvals_mbgd = mbgd(x_train, y_train, lam, learning_rate, w)

# Train regularized logistric regression
# You should get the optimal weights and a list of objective values by using gradient_descent function.

lam = 0.0001
learning_rate = 1
w = numpy.zeros((d, 1))

w_mbgd_regularized, objvals_mbgd_regularized = mbgd(x_train, y_train, lam, learning_rate, w)

"""# 4. Compare GD, SGD, MBGD

### Plot objective function values against epochs.
"""

# Commented out IPython magic to ensure Python compatibility.
import matplotlib.pyplot as plt
# %matplotlib inline

fig = plt.figure(figsize=(10, 5))

line1, = plt.plot(range(len(objvals_gd)), objvals_gd, 'y')
line2, = plt.plot(range(len(objvals_sgd)), objvals_sgd, '-.r')
line3, = plt.plot(range(len(objvals_mbgd)), objvals_mbgd, '--g')

plt.xlabel('Epochs')
plt.ylabel('Objective Value')
plt.legend([line1, line2, line3], ['GD', 'SGD', 'MBGD'])
plt.show()

# Commented out IPython magic to ensure Python compatibility.
import matplotlib.pyplot as plt
# %matplotlib inline

fig = plt.figure(figsize=(10, 5))

line4, = plt.plot(range(len(objvals_gd_regularized)), objvals_gd_regularized, 'y')
line5, = plt.plot(range(len(objvals_sgd_regularized)), objvals_sgd_regularized, '-.r')
line6, = plt.plot(range(len(objvals_mbgd_regularized)), objvals_mbgd_regularized, '--g')

plt.xlabel('Epochs')
plt.ylabel('Objective Value')
plt.legend([line4, line5, line6], ['GD_Regularized', 'SGD_Regularized', 'MBGD_Regularized'])
plt.show()

"""# 5. Prediction
### Compare the training and testing accuracy for logistic regression and regularized logistic regression.
"""

# Predict class label
# Inputs:
#     w: weights: d-by-1 matrix
#     X: data: m-by-d matrix
# Return:
#     f: m-by-1 matrix, the predictions
def predict(w, X):
    xw = numpy.dot(X, w)
    f = numpy.sign(xw)
    return f
    pass

"""### Training and Testing classification error of Gradient Descent"""

f_train = predict(w_gd, x_train)
diff = numpy.abs(f_train - y_train) / 2
error_train = numpy.mean(diff)
print('Training classification error is ' + str(error_train))

f_test = predict(w_gd, x_test)
diff = numpy.abs(f_test - y_test) / 2
error_test = numpy.mean(diff)
print('Test classification error is ' + str(error_test))

"""### Training and Testing classification error of Gradient Descent Regularized"""

f_train = predict(w_gd_regularized, x_train)
diff = numpy.abs(f_train - y_train) / 2
error_train = numpy.mean(diff)
print('Training classification error is ' + str(error_train))

f_test = predict(w_gd_regularized, x_test)
diff = numpy.abs(f_test - y_test) / 2
error_test = numpy.mean(diff)
print('Test classification error is ' + str(error_test))

"""### Training and Testing classification error of Stochastic Gradient Descent"""

f_train = predict(w_sgd, x_train)
diff = numpy.abs(f_train - y_train) / 2
error_train = numpy.mean(diff)
print('Training classification error is ' + str(error_train))

f_test = predict(w_sgd, x_test)
diff = numpy.abs(f_test - y_test) / 2
error_test = numpy.mean(diff)
print('Test classification error is ' + str(error_test))

"""### Training and Testing classification error of Stochastic Gradient Descent Regularized"""

f_train = predict(w_sgd_regularized, x_train)
diff = numpy.abs(f_train - y_train) / 2
error_train = numpy.mean(diff)
print('Training classification error is ' + str(error_train))

f_test = predict(w_sgd_regularized, x_test)
diff = numpy.abs(f_test - y_test) / 2
error_test = numpy.mean(diff)
print('Test classification error is ' + str(error_test))

"""### Training and Testing classification error of Mini-Batch Gradient Descent"""

f_train = predict(w_mbgd, x_train)
diff = numpy.abs(f_train - y_train) / 2
error_train = numpy.mean(diff)
print('Training classification error is ' + str(error_train))

f_test = predict(w_mbgd, x_test)
diff = numpy.abs(f_test - y_test) / 2
error_test = numpy.mean(diff)
print('Test classification error is ' + str(error_test))

"""### Training and Testing classification error of Mini-Batch Gradient Descent Regularized"""

f_train = predict(w_mbgd_regularized, x_train)
diff = numpy.abs(f_train - y_train) / 2
error_train = numpy.mean(diff)
print('Training classification error is ' + str(error_train))

f_test = predict(w_mbgd_regularized, x_test)
diff = numpy.abs(f_test - y_test) / 2
error_test = numpy.mean(diff)
print('Test classification error is ' + str(error_test))

"""# 6. Parameters tuning

### In this section, you may try different combinations of parameters (regularization value, learning rate, etc) to see their effects on the model. (Open ended question)
"""

