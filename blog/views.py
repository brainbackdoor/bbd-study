from django.shortcuts import get_object_or_404, render
from django.utils import timezone
from .models import Post

# Create your views here.
def post_list(request):
    
    qs = Post.objects.all()
    qs = qs.filter(published_date__lte = timezone.now())
    qs = qs.order_by('published_date')

    return render(request, 'blog/post_list.html', {
        'post_list': qs,
    })
def post_detail(request, pk):
    post = get_object_or_404(Post, pk=pk)

    return render(request, 'blog/post_detail.html', {
        'post': post
    })
