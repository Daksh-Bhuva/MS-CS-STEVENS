# -*- coding: utf-8 -*-
"""Assignment_4.ipynb

Automatically generated by Colaboratory.

Original file is located at
    https://colab.research.google.com/drive/11TdU2G8Crat7Rjzo7n1B8BRP4E0DSWW4

# Assignment 4: Build a Supervised Autoencoder.

### Name: [Daksh Bhuva]

### Due Date: Tuesday 5/3/2022 11:59PM

PCA and the standard autoencoder are unsupervised dimensionality reduction methods, and their learned features are not discriminative. If you build a classifier upon the low-dimenional features extracted by PCA and autoencoder, you will find the classification accuracy very poor.

Linear discriminant analysis (LDA) is a traditionally supervised dimensionality reduction method for learning low-dimensional features which are highly discriminative. Likewise, can we extend autoencoder to supervised leanring?

**You are required to build and train a supervised autoencoder look like the following.** You are required to add other layers properly to alleviate overfitting.


![Network Structure](https://github.com/wangshusen/CS583A-2019Spring/blob/master/homework/HM5/supervised_ae.png?raw=true "NetworkStructure")

## 0. You will do the following:

1. Build a standard dense autoencoder, visual the low-dim features and the reconstructions, and evaluate whether the learned low-dim features are discriminative.

2. Repeat the above process by training a supervised autoencoder.

## 1. Data preparation

### 1.1. Load data
"""

from keras.datasets import mnist

(x_train, y_train), (x_test, y_test) = mnist.load_data()
x_train = x_train.reshape(60000, 28*28).astype('float32') / 255.
x_test = x_test.reshape(10000, 28*28).astype('float32') / 255.

print('Shape of x_train: ' + str(x_train.shape)) 
print('Shape of x_test: ' + str(x_test.shape))
print('Shape of y_train: ' + str(y_train.shape))
print('Shape of y_test: ' + str(y_test.shape))

"""### 1.2. One-hot encode the labels

In the input, a label is a scalar in $\{0, 1, \cdots , 9\}$. One-hot encode transform such a scalar to a $10$-dim vector. E.g., a scalar ```y_train[j]=3``` is transformed to the vector ```y_train_vec[j]=[0, 0, 0, 1, 0, 0, 0, 0, 0, 0]```.

1. Define a function ```to_one_hot``` that transforms an $n\times 1$ array to a $n\times 10$ matrix.

2. Apply the function to ```y_train``` and ```y_test```.
"""

import numpy as np

def to_one_hot(y, num_class=10):
    results = np.zeros((len(y), num_class))
    for i, label in enumerate(y):
        results[i, label] = 1.
    return results

y_train_vec = to_one_hot(y_train)
y_test_vec = to_one_hot(y_test)

print('Shape of y_train_vec: ' + str(y_train_vec.shape))
print('Shape of y_test_vec: ' + str(y_test_vec.shape))

print(y_train[0])
print(y_train_vec[0])

"""### 1.3. Randomly partition the training set to training and validation sets

Randomly partition the 60K training samples to 2 sets:
* a training set containing 10K samples;
* a validation set containing 50K samples. (You can use only 10K to save time.)

"""

rand_indices = np.random.permutation(60000)
train_indices = rand_indices[0:10000]
valid_indices = rand_indices[10000:20000]

x_val = x_train[valid_indices, :]
y_val = y_train_vec[valid_indices, :]

x_tr = x_train[train_indices, :]
y_tr = y_train_vec[train_indices, :]

print('Shape of x_tr: ' + str(x_tr.shape))
print('Shape of y_tr: ' + str(y_tr.shape))
print('Shape of x_val: ' + str(x_val.shape))
print('Shape of y_val: ' + str(y_val.shape))

"""## 2. Build an unsupervised  autoencoder and tune its hyper-parameters

1. Build a dense autoencoder model
2. Your encoder should contain 3 dense layers and 1 bottlenect layer with 2 as  output size. 
3. Your decoder should contain 4 dense layers with 784 as output size.
4. You can choose different number of hidden units in dense layers.
5. Do not add other layers (no activation layers), you may add them in later sections.
6. Use the validation data to tune the hyper-parameters (e.g., network structure, and optimization algorithm)
    * Do NOT use test data for hyper-parameter tuning!!!
    
7. Try to achieve a validation loss as low as possible.
8. Evaluate the model on the test set.
9. Visualize the low-dim features and reconstructions.

### 2.1. Build the model (20 points)
"""

from keras.layers import *
from keras import models

input_img = Input(shape=(784,), name='input_img')

en_code_1 = Dense(128, activation='relu', name='en_code_1')(input_img)
en_code_2 = Dense(32, activation='relu', name='en_code_2')(en_code_1)
en_code_3 = Dense(8, activation='relu', name='en_code_3')(en_code_2)

bottle_neck = Dense(2, activation='relu', name='bottle_neck')(en_code_3)

de_code_1 = Dense(8, activation='relu', name='de_code_1')(bottle_neck)
de_code_2 = Dense(32, activation='relu', name='de_code_2')(de_code_1)
de_code_3 = Dense(128, activation='relu', name='de_code_3')(de_code_2)
de_code_4 = Dense(784, activation='relu', name='de_code_4')(de_code_3)

ae = models.Model(input_img, de_code_4)

ae.summary()

# print the network structure to a PDF file

from IPython.display import SVG
from keras.utils.vis_utils import model_to_dot, plot_model

SVG(model_to_dot(ae, show_shapes=False).create(prog='dot', format='svg'))

plot_model(
    model=ae, show_shapes=False,
    to_file='unsupervised_ae.pdf'
)

# you can find the file "unsupervised_ae.pdf" in the current directory.

"""### 2.2. Train the model and tune the hyper-parameters (5 points)"""

from tensorflow.keras import optimizers

learning_rate = 1E-3

ae.compile(loss='mean_squared_error',
           optimizer=optimizers.RMSprop(lr=learning_rate))

history = ae.fit(x_tr, x_tr, 
                 batch_size=128, 
                 epochs=100, 
                 validation_data=(x_val, x_val))

# Commented out IPython magic to ensure Python compatibility.
import matplotlib.pyplot as plt
# %matplotlib inline

loss = history.history['loss']
val_loss = history.history['val_loss']

epochs = range(len(loss))

plt.plot(epochs, loss, 'bo', label='Training Loss')
plt.plot(epochs, val_loss, 'r', label='Validation Loss')
plt.xlabel('Epochs')
plt.ylabel('Loss')
plt.legend()
plt.show()

"""### 2.3. Visualize the reconstructed test images (5 points)"""

ae_output = ae.predict(x_test).reshape((10000, 28, 28))

ROW = 5
COLUMN = 4

x = ae_output
fname = 'reconstruct_ae.pdf'

fig, axes = plt.subplots(nrows=ROW, ncols=COLUMN, figsize=(8, 10))
for ax, i in zip(axes.flat, np.arange(ROW*COLUMN)):
    image = x[i].reshape(28, 28)
    ax.imshow(image, cmap='gray')
    ax.axis('off')

plt.tight_layout()
plt.savefig(fname)
plt.show()

"""### 2.4. Evaluate the model on the test set

Do NOT used the test set until now. Make sure that your model parameters and hyper-parameters are independent of the test set.
"""

loss = ae.evaluate(x_test, x_test)
print('loss = ' + str(loss))

"""### 2.5. Visualize the low-dimensional features"""

# build the encoder network
ae_encoder = models.Model(input_img, bottle_neck)
ae_encoder.summary()

# extract low-dimensional features from the test data
encoded_test = ae_encoder.predict(x_test)
print('Shape of encoded_test: ' + str(encoded_test.shape))

# Commented out IPython magic to ensure Python compatibility.
colors = np.array(['r', 'g', 'b', 'm', 'c', 'k', 'y', 'purple', 'darkred', 'navy'])
colors_test = colors[y_test]


import matplotlib.pyplot as plt
# %matplotlib inline

fig = plt.figure(figsize=(8, 8))
plt.scatter(encoded_test[:, 0], encoded_test[:, 1], s=10, c=colors_test, edgecolors=colors_test)
plt.axis('off')
plt.tight_layout()
fname = 'ae_code.pdf'
plt.savefig(fname)

"""#### Remark:

Judging from the visualization, the low-dim features seems not discriminative, as 2D features from different classes are mixed. Let quantatively find out whether they are discriminative.

## 3. Are the learned low-dim features discriminative? (10 points)

To find the answer, lets train a classifier on the training set (the extracted 2-dim features) and evaluation on the test set.
"""

# extract the 2D features from the training, validation, and test samples
f_tr = ae_encoder.predict(x_tr)
f_val = ae_encoder.predict(x_val)
f_te = ae_encoder.predict(x_test)

print('Shape of f_tr: ' + str(f_tr.shape))
print('Shape of f_te: ' + str(f_te.shape))

from keras.layers import Dense, Input
from keras import models

input_feat = Input(shape=(2,))

hidden_1 = Dense(128, activation='relu')(input_feat)
hidden_2 = Dense(128, activation='relu')(hidden_1)
output = Dense(10, activation='softmax')(hidden_2)

classifier = models.Model(input_feat, output)

classifier.summary()

classifier.compile(loss='categorical_crossentropy',
                  optimizer=optimizers.RMSprop(lr=1E-4),
                  metrics=['acc'])

history = classifier.fit(f_tr, y_tr, 
                        batch_size=32, 
                        epochs=30, 
                        validation_data=(f_val, y_val))

"""### Conclusion

Using the 2D features, the validation accuracy is 60~70%. Recall that using the original data, the accuracy is about 97%. Obviously, the 2D features are not very discriminative.

We are going to build a supervised autoencode model for learning low-dimensional discriminative features.

## 4. Build a supervised autoencoder model


**You are required to build and train a supervised autoencoder look like the following.** (Not necessary the same. You can use convolutional layers as well.) You are required to add other layers properly to alleviate overfitting.


![Network Structure](https://github.com/wangshusen/CS583A-2019Spring/blob/master/homework/HM5/supervised_ae.png?raw=true "NetworkStructure")

### 4.1. Build the network (30 points)
"""

# build the supervised autoencoder network
from keras.layers import *
from keras import models

input_img = Input(shape=(784,), name='input_img')

# encoder network

en_code_1 = Dense(128, name = 'en_code_1')(input_img)
en_code_1 = BatchNormalization()(en_code_1)
en_code_1 = Activation('relu')(en_code_1)
en_code_1 = Dropout(0.2)(en_code_1)

en_code_2 = Dense(32, name = 'en_code_2')(en_code_1)
en_code_2 = BatchNormalization()(en_code_2)
en_code_2 = Activation('relu')(en_code_2)
en_code_2 = Dropout(0.2)(en_code_2)

en_code_3 = Dense(8, name = 'en_code_3')(en_code_2)
en_code_3 = BatchNormalization()(en_code_3)
en_code_3 = Activation('relu')(en_code_3)

# The width of the bottle_neck layer must be exactly 2.

bottle_neck = Dense(2, name = 'bottle_neck')(en_code_3)
bottle_neck = BatchNormalization()(bottle_neck)
bottle_neck = Activation('relu')(bottle_neck)

# decoder network

input_dec = Input(shape=(2,))
de_code_1 = Dense(8, name='de_code_1')(input_dec)
de_code_1 = BatchNormalization()(de_code_1)
de_code_1 = Activation('relu')(de_code_1)

de_code_2 = Dense(32, name='de_code_2')(de_code_1)
de_code_2 = BatchNormalization()(de_code_2)
de_code_2 = Activation('relu')(de_code_2)
de_code_2 = Dropout(0.4)(de_code_2)

de_code_3 = Dense(128, name='de_code_3')(de_code_2)
de_code_3 = BatchNormalization()(de_code_3)
de_code_3 = Activation('relu')(de_code_3)
de_code_3 = Dropout(0.2)(de_code_3)

de_code_4 = Dense(784, name='de_code_4')(de_code_3)
de_code_4 = BatchNormalization()(de_code_4)
de_code_4 = Activation('relu')(de_code_4)

# build a classifier upon the bottle_neck layer

input_feat = Input(shape=(2,))
hidden_1 = Dense(128)(input_feat)
hidden_1 = BatchNormalization()(hidden_1)
hidden_1 = Activation('relu')(hidden_1)
hidden_1 = Dropout(0.2)(hidden_1)

hidden_2 = Dense(128)(hidden_1)
hidden_2 = BatchNormalization()(hidden_2)
hidden_2 = Activation('relu')(hidden_2)
hidden_2 = Dropout(0.5)(hidden_2)

output = Dense(10, activation='softmax')(hidden_2)

sae_encoder = models.Model(input_img, bottle_neck)
sae_decoder = models.Model(input_dec, de_code_4)
classifer = models.Model(input_feat, output)

sae_bottle_neck = sae_encoder(input_img)
dec = sae_decoder(sae_bottle_neck)
cls = classifer(sae_bottle_neck)

# connect the input and the two outputs
sae = models.Model(input_img, [dec, cls])

sae.summary()

# print the network structure to a PDF file

from IPython.display import SVG
from keras.utils.vis_utils import model_to_dot, plot_model

SVG(model_to_dot(sae, show_shapes=False).create(prog='dot', format='svg'))

plot_model(
    model=sae, show_shapes=False,
    to_file='supervised_ae.pdf'
)

# you can find the file "supervised_ae.pdf" in the current directory.

"""### 4.2. Train the new model and tune the hyper-parameters

The new model has multiple output. Thus we specify **multiple** loss functions and their weights. 
"""

from tensorflow.keras import optimizers

sae.compile(loss=['mean_squared_error', 'categorical_crossentropy'],
            loss_weights=[1, 0.5], # to be tuned
            optimizer=optimizers.RMSprop(lr=1E-3))

history = sae.fit(x_tr, [x_tr, y_tr], 
                  batch_size=32, 
                  epochs=100, 
                  validation_data=(x_val, [x_val, y_val]))

# Commented out IPython magic to ensure Python compatibility.
import matplotlib.pyplot as plt
# %matplotlib inline

loss = history.history['loss']
val_loss = history.history['val_loss']

epochs = range(len(loss))

plt.plot(epochs, loss, 'bo', label='Training Loss')
plt.plot(epochs, val_loss, 'r', label='Validation Loss')
plt.xlabel('Epochs')
plt.ylabel('Loss')
plt.legend()
plt.show()

"""### Question  (10 points)

Do you think overfitting is happening? If yes, what can you do? Please make necessary changes to the supervised autoencoder network structure.

You can use the new model without overfitting for the following sections.

=> Yes, overfitting is happening. So, I have added more hidden layers and used techniques like Batch Normalization and Dropout to the supervised autoencoder network structure to overcome that.

### 4.3. Visualize the reconstructed test images
"""

sae_output = sae.predict(x_test)[0].reshape((10000, 28, 28))

ROW = 5
COLUMN = 4

x = sae_output
fname = 'reconstruct_sae.pdf'

fig, axes = plt.subplots(nrows=ROW, ncols=COLUMN, figsize=(8, 10))
for ax, i in zip(axes.flat, np.arange(ROW*COLUMN)):
    image = x[i].reshape(28, 28)
    ax.imshow(image, cmap='gray')
    ax.axis('off')

plt.tight_layout()
plt.savefig(fname)
plt.show()

"""### 4.4. Visualize the low-dimensional features


"""

# build the encoder model
sae_encoder = models.Model(input_img, bottle_neck)
sae_encoder.summary()

# Commented out IPython magic to ensure Python compatibility.
# extract test features
encoded_test = sae_encoder.predict(x_test)
print('Shape of encoded_test: ' + str(encoded_test.shape))

colors = np.array(['r', 'g', 'b', 'm', 'c', 'k', 'y', 'purple', 'darkred', 'navy'])
colors_test = colors[y_test]


import matplotlib.pyplot as plt
# %matplotlib inline

fig = plt.figure(figsize=(8, 8))
plt.scatter(encoded_test[:, 0], encoded_test[:, 1], s=10, c=colors_test, edgecolors=colors_test)
plt.axis('off')
plt.tight_layout()
fname = 'sae_code.pdf'
plt.savefig(fname)

"""### 4.5. Are the learned low-dim features discriminative? (10 points)

To find the answer, lets train a classifier on the training set (the extracted 2-dim features) and evaluation on the validation and test set.


"""

# extract 2D features from the training, validation, and test samples
f_tr = sae_encoder.predict(x_tr)
f_val = sae_encoder.predict(x_val)
f_te = sae_encoder.predict(x_test)

# build a classifier which takes the 2D features as input
from keras.layers import *
from keras import models

input_feat = Input(shape=(2,))

hidden_1 = Dense(128, activation='relu')(input_feat)
do1 = Dropout(0.4)(hidden_1)
hidden_2 = Dense(128, activation='relu')(do1)
output = Dense(10, activation='softmax')(hidden_2)

classifier = models.Model(input_feat, output)

classifier.summary()

classifier.compile(loss='categorical_crossentropy',
                  optimizer=optimizers.RMSprop(lr=1E-4),
                  metrics=['acc'])

history = classifier.fit(f_tr, y_tr, 
                        batch_size=32, 
                        epochs=30, 
                        validation_data=(f_val, y_val))

"""#### Remark: (10 points)

The validation accuracy must be above 90%. It means the low-dim features learned by the supervised autoencoder are very effective.
"""

# evaluate your model on the never-seen-before test data
# write your code here:

loss_acc = classifier.evaluate(f_te, y_test_vec)

print('Accuracy:', loss_acc[1])