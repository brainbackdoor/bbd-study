from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.

def mysum(request, x, y =0, z=0):
    print(x, y,z)
    return HttpResponse(int(x) + int(y) + int(z))