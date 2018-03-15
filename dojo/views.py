from django.shortcuts import render
from django.http import HttpResponse
# Create your views here.

def mysum(request, numbers):
    return HttpResponse(sum(map(lambda s: int(s or 0), numbers.split("/"))))