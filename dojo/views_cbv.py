from django.http import HttpResponse
from django.views.generic import View, TemplateView

class PostListView1(View):
    def get(self, request):
        name = '공유'
        html = self.get_template_string().format(name = name)
        return HttpResponse(html)
    
    def get_template_string(self):
        return '''
            <h1>AskDjango</h1>
            <p>{name}</p>
            <p>파이썬!! </p>        
        '''

post_list1 = PostListView1.as_view()


class PostListView2(TemplateView):
    template_name = 'dojo/post_list.html'
    def get_context_data(self):
        context = super().get_context_data()
        context['name'] = '공유'
        return context
post_list2 = PostListView2.as_view()
    
class PostListView3(object):
    pass
class ExcelDownloadView(object):
    pass