
from django.shortcuts import render
from django.http import HttpResponse, JsonResponse
from django.conf import settings
import os

# Create your views here.

def mysum(request, numbers):
    return HttpResponse(sum(map(lambda s: int(s or 0), numbers.split("/"))))


def hello(request, name, age):
    return HttpResponse('안녕하세요. {}. {}살이시네요.'.format(name,age))



def post_list1(request):
    'FBV: 직접 문자열로 HTML 형식 응답하기'

    name = '공유'
    return HttpResponse('''
    <h1>AskDjango</h1>
    <p>{name}</p>
    <p>파이썬!! </p>
    '''.format(name=name))


def post_list2(request):
    'FBV: 템플릿을 통해 HTML 형식 응답하기'

    name = '공유'
    response = render(request, 'dojo/post_list.html', {'name': name})
    return response


def post_list3(request):
    'FBV: JSON 형식으로 응답하기'

    return JsonResponse({
        'message': '안뇽',
        'items': ['파이썬', '장고'],
    }, json_dumps_params={'ensure_ascii': False})


def excel_download(request):
    'FBV: 엑셀 다운로드 응답하기'

    # filepath = '/other/path/excel.xls'
    filepath = os.path.join(settings.BASE_DIR, 'gdplev.xls')
    filename = os.path.basename(filepath)

    with open(filepath, 'rb') as f:
        respoonse = HttpResponse(f, content_type = 'application/vnd.ms-excel')

        response['Content-Disposition'] = 'attachment; filename="{}"'.format(filename)
        return response