package br.com.nova.jogos.unittests.mapper;

import java.util.ArrayList;
import java.util.List;

import br.com.nova.jogos.data.vo.v1.PostagemVO;
import br.com.nova.jogos.model.Postagem;

public class MockPosts {


    public Postagem mockEntity() {
        return mockEntity(0.0);
    }
    
    public PostagemVO mockVO() {
        return mockVO(0.0);
    }
    
    public List<Postagem> mockEntityList() {
        List<Postagem> postagens = new ArrayList<Postagem>();
        for (double i = 0; i < 14; i++) {
            postagens.add(mockEntity(i));
        }
        return postagens;
    }

    public List<PostagemVO> mockVOList() {
        List<PostagemVO> postagens = new ArrayList<>();
        for (double i = 0; i < 14; i++) {
            postagens.add(mockVO(i));
        }
        return postagens;
    }
    
    public Postagem mockEntity(Double number) {
        Postagem postagem = new Postagem();
        postagem.setAutor("Autor test" + number);
        postagem.setTitulo("Titulo test" + number);
        postagem.setId(number.longValue());
        postagem.setDescricao("Descrição test" + number);
        return postagem;
    }

    public PostagemVO mockVO(Double number) {
        PostagemVO postagem = new PostagemVO();
        postagem.setAutor("Autor test" + number);
        postagem.setTitulo("Titulo test" + number);
        postagem.setKey(number.longValue());
        postagem.setDescricao("Descrição test" + number);
        return postagem;
    }

}
