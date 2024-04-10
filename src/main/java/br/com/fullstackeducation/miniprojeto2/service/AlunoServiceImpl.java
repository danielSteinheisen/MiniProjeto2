package br.com.fullstackeducation.miniprojeto2.service;

import br.com.fullstackeducation.miniprojeto2.dto.AlunoFiltro;
import br.com.fullstackeducation.miniprojeto2.entity.AlunoEntity;
import br.com.fullstackeducation.miniprojeto2.entity.ProfessorEntity;
import br.com.fullstackeducation.miniprojeto2.exception.NotFoundException;
import br.com.fullstackeducation.miniprojeto2.repository.AlunoRepository;
import br.com.fullstackeducation.miniprojeto2.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class AlunoServiceImpl implements AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoServiceImpl(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

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
            return alunoRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("Aluno não encontrado com o ID: " + id));
        }
        log.info("Buscando aluno por id ({}) -> Encontrado", id);
        log.debug("Buscando aluno por id ({}) -> Registro encontrado:\n{}\n", id,
                JsonUtil.objetoParaJson(aluno.get()));
        return aluno.get();
    }

    @Override
    public AlunoEntity atualizarAluno(Long id, AlunoEntity aluno) {
        if (!alunoRepository.existsById(id)) {
            log.info("Alterando aluno com id ({}) -> Salvar: \n{}\n", id, JsonUtil.objetoParaJson(aluno));
            throw new NotFoundException("Aluno não encontrado com o ID: " + id);
        }
        aluno.setId(id);
        log.info("Alterando aluno -> Salvo com sucesso");
        log.debug("Alterando aluno -> Registro Salvo: \n{}\n", JsonUtil.objetoParaJson(aluno));
        return alunoRepository.save(aluno);
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
