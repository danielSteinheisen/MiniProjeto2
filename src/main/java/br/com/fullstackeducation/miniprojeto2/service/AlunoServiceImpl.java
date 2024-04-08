package br.com.fullstackeducation.miniprojeto2.service;

import br.com.fullstackeducation.miniprojeto2.dto.AlunoFiltro;
import br.com.fullstackeducation.miniprojeto2.entity.AlunoEntity;
import br.com.fullstackeducation.miniprojeto2.exception.error.AlunoByIdNotFoundException;
import br.com.fullstackeducation.miniprojeto2.exception.error.AlunoByNomeNotFoundException;
import br.com.fullstackeducation.miniprojeto2.exception.error.NotFoundException;
import br.com.fullstackeducation.miniprojeto2.repository.AlunoRepository;
import br.com.fullstackeducation.miniprojeto2.util.JsonUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository alunoRepository;

    @Override
    public AlunoEntity criarAluno(AlunoEntity alunoNovo) {
        alunoNovo.setId(null);
        log.info("Criando aluno -> Salvar: \n{}\n", JsonUtil.objetoParaJson(alunoNovo));
        AlunoEntity aluno = alunoRepository.save(alunoNovo);
        log.info("Criando aluno -> Salvo com sucesso");
        log.debug("Criando aluno -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(alunoNovo));
        return aluno;
    }

    @Override
    public List<AlunoEntity> listarAlunos(AlunoFiltro filtro) {
        List<AlunoEntity> alunos;

        if (StringUtils.hasText(filtro.getNome())) {
            log.info("Buscando todos os alunos -> com nome ({})", filtro.getNome());
            alunos = alunoRepository.findByNomeContainingIgnoreCase(filtro.getNome());
        } else {
            log.info("Buscando todos os alunos");
            alunos = alunoRepository.findAll();
        }

        log.info("Buscando todos os alunos -> {} alunos encontrados", alunos.size());
        log.debug("Buscando todos os alunos -> Registros encontrados:\n{}\n",
                JsonUtil.objetoParaJson(alunos));
        return alunos;
    }

    @Override
    public AlunoEntity buscarAlunoPorId(Long id) {
        log.info("Buscando aluno por ID: {}", id);
        Optional<AlunoEntity> aluno = alunoRepository.findById(id);

        if (aluno.isEmpty()) {
            log.error("Buscando aluno por id {} -> NÃO Encontrado", id);
            throw new AlunoByIdNotFoundException(id);
        }
        log.info("Buscando aluno por id ({}) -> Encontrado", id);
        log.debug("Buscando aluno por id ({}) -> Registro encontrado:\n{}\n", id,
                JsonUtil.objetoParaJson(aluno.get()));
        return aluno.get();
    }

    public AlunoEntity buscarAlunoPorNome(String nome) {
        log.info("Buscando aluno por Nome: {}", nome);
        Optional<AlunoEntity> opt = alunoRepository.findTop1ByNome(nome);

        if (opt.isEmpty()) {
            log.error("Buscando aluno por nome {} -> NÃO Encontrado", nome);
            throw new AlunoByNomeNotFoundException(nome);
        }

        log.info("Buscando aluno por nome ({}) -> Encontrado", nome);
        log.debug("Buscando aluno por nome ({}) -> Registro encontrado:\n{}\n", nome,
                JsonUtil.objetoParaJson(opt.get()));
        return opt.get();
    }

    @Override
    public AlunoEntity atualizarAluno(Long id, AlunoEntity aluno) {

        AlunoEntity entity = buscarAlunoPorId(id);
        entity.setNome(aluno.getNome());
        entity.setNascimento(aluno.getNascimento());

        log.info("Alterando aluno com id ({}) -> Salvar: \n{}\n", id, JsonUtil.objetoParaJson(entity));
        entity = alunoRepository.save(entity);

        log.info("Alterando aluno -> Salvo com sucesso");
        log.debug("Alterando aluno -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(entity));
        return entity;
    }

    @Override
    public void excluirAluno(Long id) {

        if (!alunoRepository.existsById(id)) {
            log.info("Excluindo aluno com id ({}) -> Excluindo", id);
            throw new NotFoundException("Aluno não encontrada com o ID: " + id);
        }

        alunoRepository.deleteById(id);
        log.info("Excluindo aluno com id ({}) -> Excluído com sucesso", id);
    }
}
