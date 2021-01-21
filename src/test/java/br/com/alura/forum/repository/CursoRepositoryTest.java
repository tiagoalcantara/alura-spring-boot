package br.com.alura.forum.repository;

import br.com.alura.forum.modelo.Curso;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
public class CursoRepositoryTest {

    @Autowired
    private CursoRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    public void deveriaCarregarUmCursoAoBuscarPorNome() {
        String nomeNovoCurso = "HTML 5";

        Curso novoCurso = new Curso();
        novoCurso.setNome(nomeNovoCurso);
        novoCurso.setCategoria("Programação");
        em.persist(novoCurso);

        Curso curso = repository.findByNome(nomeNovoCurso);
        Assert.assertNotNull(curso);
        Assert.assertEquals(nomeNovoCurso, curso.getNome());
    }

    @Test
    public void naoDeveriaCarregarUmCursoSeONomeNaoExiste() {
        String nomeCurso = "JPA";
        Curso curso = repository.findByNome(nomeCurso);
        Assert.assertNull(curso);
    }
}