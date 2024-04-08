package br.com.fullstackeducation.miniprojeto2.service;

import br.com.fullstackeducation.miniprojeto2.dto.AlunoFiltro;
import br.com.fullstackeducation.miniprojeto2.entity.AlunoEntity;
import br.com.fullstackeducation.miniprojeto2.exception.error.NotFoundException;
import br.com.fullstackeducation.miniprojeto2.repository.AlunoRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.type.descriptor.DateTimeUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AlunoServiceImpl  implements AlunoService{

    private final AlunoRepository repository;

    @Override
    public AlunoEntity criar(AlunoEntity aluno) throws Exception {
        aluno.setId(null);
        return repository.save(aluno);
    }

    @Override
    public List<AlunoEntity> buscarTodos(AlunoFiltro filtro) {
        if (StringUtils.hasText(filtro.getNome())) {
            return repository.findByNomeContainingIgnoreCase(filtro.getNome());
        }
        return repository.findAll();
    }

    @Override
    public AlunoEntity buscarPorId(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Aluno de id" + id + " não encontrado."));
    }

    @Override
    public AlunoEntity buscarPorNome(String nome) {
        Optional<AlunoEntity> opt = repository.findTop1ByNome(nome);
        return opt.orElseThrow(() -> new NotFoundException("Aluno" + nome + " não encontrado"));
    }

    @Override
    public AlunoEntity alterar(Long id, AlunoEntity aluno) {
        AlunoEntity entity = buscarPorId(id);
        entity.setNome(aluno.getNome());
        entity.setNascimento(aluno.getNascimento());

        return repository.save(entity);
    }

    @Override
    public void apagar(Long id) {
        AlunoEntity entity = buscarPorId(id);
        repository.delete(entity);
    }
}
